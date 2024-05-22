import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
public class EcosystemSimulation extends Observable {
    private int width;
    private int height;
    private List<Animal> animals;
    private Environment environment;
    private List<String> eventLog;
    private Random random;

    public EcosystemSimulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.environment = new Environment(100, 100); // przykładowe początkowe wartości
        this.animals = new ArrayList<>();
        this.eventLog = new ArrayList<>();
        this.random = new Random();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
//        setChanged(); // Notify observers only if there's a change
        notifyObservers("animalAdded" + animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        notifyObservers("animalRemoved" + animal);
    }

    public void addWeatherCondition(Environment condition) {
        notifyObservers("weatherConditionAdded" + condition);
    }
    public List<Animal> getAnimals() {
        return animals;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void simulate() {
        updateStates();
        generateRandomEvents();
    }

    private void updateStates() {
        for (Animal animal : animals) {
            animal.move();
            notifyObservers("animalMoved" + animal);
        }
        // Additional state updates for weather conditions can be added here
    }

    private void generateRandomEvents() {
        int eventType = random.nextInt(3);
        switch (eventType) {
            case 0:
                Drought drought = new Drought(random.nextInt(10) + 1);
                drought.dry(environment);
                eventLog.add("Drought affected water level: -" + drought.getStrength());
                setChanged();
                notifyObservers("drought: " + drought);
                break;
            case 1:
                int waterAmount = random.nextInt(10) + 1;
                Rain rain = new Rain(waterAmount);
                rain.destroy(environment);
                eventLog.add("Rain affected water level: +" + waterAmount);
                setChanged();
                notifyObservers("rain: " + rain);
                break;
            case 2:
                Storm storm = new Storm(random.nextInt(10) + 1);
                storm.place(environment);
                eventLog.add("Storm affected light level");
                setChanged();
                notifyObservers("storm: " + storm);
                break;
        }
        for (Animal animal : animals) {
            switch (eventType) {
                case 0:
                    animal.reactToEvent("drought");
                    break;
                case 1:
                    animal.reactToEvent("rain");
                    break;
                case 2:
                    animal.reactToEvent("storm");
                    break;
            }
            notifyObservers("animalReacted" + animal);
        }
    }

    // Additional methods for the simulation can be added here
    public void printPopulation() {
        System.out.println("Population:");
        for (Animal animal : animals) {
            System.out.println(animal.getClass().getSimpleName() + " at " + animal.getPosition().getX() + ", " + animal.getPosition().getY());
        }
    }

    public void saveSimulationDataToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Animal,Energy,Health,Diet,Position,Wingspan/IsPackLeader/HasAntlers\n");
            for (Animal animal : animals) {
                writer.append(animal.getClass().getSimpleName())
                        .append(',')
                        .append(String.valueOf(animal.getEnergy()))
                        .append(',')
                        .append(String.valueOf(animal.getHealth()))
                        .append(',')
                        .append(animal.getDiet())
                        .append(',')
                        .append(animal.getPosition().toString())
                        .append(',');

                if (animal instanceof Wolf) {
                    writer.append(String.valueOf(((Wolf) animal).isPackLeader()));
                } else if (animal instanceof Deer) {
                    writer.append(String.valueOf(((Deer) animal).hasAntlers()));
                } else if (animal instanceof Bird) {
                    writer.append(String.valueOf(((Bird) animal).getWingspan()));
                }

                writer.append('\n');
            }

            writer.append("Environment,WaterLevel,LightLevel\n");
            writer.append("Environment,")
                    .append(String.valueOf(environment.getWaterLevel()))
                    .append(',')
                    .append(String.valueOf(environment.getLightLevel()))
                    .append('\n');

            writer.append("EventLog\n");
            for (String event : eventLog) {
                writer.append(event).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EcosystemSimulation simulation = new EcosystemSimulation(100, 100);

        // Dodawanie obserwatora
        Observer consoleObserver = new ConsoleObserver();
        simulation.addObserver(consoleObserver);

        // Dodawanie zwierząt
        simulation.addAnimal(new Wolf(100, 100, new Point(0, 0), "carnivore"));
        simulation.addAnimal(new Deer(100, 100, new Point(10, 10), "herbivore"));
        simulation.addAnimal(new Bird(100, 100, new Point(20, 20), "omnivore"));

        // Uruchomienie symulacji
        simulation.simulate();

        // Wyświetlenie populacji
        simulation.printPopulation();

        // Zapis danych symulacji do pliku CSV
        simulation.saveSimulationDataToCSV("simulation_data.csv");
    }
}