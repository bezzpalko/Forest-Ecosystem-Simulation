package ecosystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
public class EcosystemSimulation extends Observable {
    final int width;
    final int height;
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
        setChanged(); // Notify observers only if there's a change
        notifyObservers("animalAdded" + animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        setChanged();
        notifyObservers("animalRemoved" + animal);
    }

    public void addWeatherCondition(WeatherEvent event) {
        setChanged();
        notifyObservers("weatherConditionAdded" + event);
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
        setChanged();
        notifyObservers("Simulation step completed.");
    }

    private void updateStates() {
        for (Animal animal : animals) {
            animal.move();
            setChanged();
            notifyObservers("animalMoved" + animal);
        }
    }

    private void generateRandomEvents() {
//        int eventType = new Random().nextInt(3);
//        switch (eventType) {
//            case 0:
//                ecosystem.Drought drought = new ecosystem.Drought();
//                drought.dry(environment);
//                eventLog.add("ecosystem.Drought affected water level: -" + drought.getStrength());
//                setChanged();
//                notifyObservers("drought: " + drought);
//                break;
//            case 1:
//                ecosystem.Rain rain = new ecosystem.Rain();
//                rain.destroy(environment);
//                eventLog.add("ecosystem.Rain affected water level: +" + rain.getWater());
//                setChanged();
//                notifyObservers("rain: " + rain);
//                break;
//            case 2:
//                ecosystem.Storm storm = new ecosystem.Storm();
//                storm.place(environment);
//                eventLog.add("ecosystem.Storm affected light level");
//                setChanged();
//                notifyObservers("storm: " + storm);
//                break;
//        }
        int eventType = random.nextInt(3);
        WeatherEvent event;
        switch (eventType) {
            case 0:
                event = new Drought();
                break;
            case 1:
                event = new Rain();
                break;
            case 2:
                event = new Storm();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
        event.apply(environment);
        eventLog.add(event.toString());
        addWeatherCondition(event);


        for (Animal animal : animals) {
//            switch (eventType) {
//                case 0:
//                    animal.reactToEvent("drought");
//                    break;
//                case 1:
//                    animal.reactToEvent("rain");
//                    break;
//                case 2:
//                    animal.reactToEvent("storm");
//                    break;
//            }
            animal.reactToEvent(event.toString());
            notifyObservers("animalReacted" + animal);
        }
    }

    public void printPopulation() {
        System.out.println("Population:");
        for (Animal animal : animals) {
            System.out.println(animal.getClass().getSimpleName() + " at " + animal.getPosition().getX() + ", " + animal.getPosition().getY());
        }
    }

    public void saveSimulationDataToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("ecosystem.Animal,Energy,Health,Diet,Position,Wingspan/IsPackLeader/HasAntlers\n");
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

        simulation.addAnimal(new Wolf(100, 100, new Point(0, 0), "carnivore"));
        simulation.addAnimal(new Deer(100, 100, new Point(10, 10), "herbivore"));
        simulation.addAnimal(new Bird(100, 100, new Point(20, 20), "omnivore"));

        simulation.simulate();

        simulation.printPopulation();

        simulation.saveSimulationDataToCSV("simulation_data.csv");
    }
}