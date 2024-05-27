package ecosystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
public class EcosystemSimulation extends Observable {
    final int width;
    final int height;
    private List<Animal> animals;
    private List<Plant> plants;
    private Environment environment;
    private List<String> weatherEvents;
    private Random random;
    private Scanner scanner;


    public EcosystemSimulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.environment = new Environment(100, 100); // przykładowe początkowe wartości
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.weatherEvents = new ArrayList<>();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
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

    public void addPlant(Plant plant) {
        plants.add(plant);
        setChanged();
        notifyObservers("plantAdded" + plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
        setChanged();
        notifyObservers("plantRemoved" + plant);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public Environment getEnvironment() {
        return environment;
    }
//    public List<String> getEventLog() {
//        return eventLog;
//    }

    public void simulate() { //zaktualizuj, wygeneruj zdarzenia, ustaw zmiany
        updateStates();
        generateEvents();
        setChanged();
        notifyObservers("Simulation step completed.");
    }

    private void updateStates() {
        for (Animal animal : animals) {
            animal.move();
            setChanged();
            notifyObservers("animalMoved" + animal);
        }

        for (Plant plant : plants) {
            plant.move();
            setChanged();
            notifyObservers("plantMoved" + plant);
        }
    }

    public void generateEvents() {
        System.out.println("Wybierz typ zdarzenia pogodowego do wywolania:");
        System.out.println("1. Susza (Drought)");
        System.out.println("2. Deszcz (Rain)");
        System.out.println("3. Burza (Storm)");
        int eventType = scanner.nextInt();
        scanner.nextLine(); // consume newline

        WeatherEvent event;
        switch (eventType) {
            case 1:
                event = new Drought();
                break;
            case 2:
                event = new Rain();
                break;
            case 3:
                event = new Storm();
                break;
            default:
                System.out.println("Nieprawidlowy wybor, prosze wybrac numer od 1 do 3.");
                return; // Zakończenie metody, jeśli wybrano nieprawidłową opcję
        }

        applyEventToEnvironment(event);
    }

    public void applyEventToEnvironment(WeatherEvent event) { //odanie zdarzenia do środowiska
        event.apply(environment);


        String eventDetails = "Zdarzenie: " + event.getClass().getSimpleName() + " {";
        if (event instanceof Drought) {
            eventDetails += "strength=" + ((Drought) event).getStrength();
        } else if (event instanceof Rain) {
            eventDetails += "water=" + ((Rain) event).getWater();
        } else if (event instanceof Storm) {
            eventDetails += "power=" + ((Storm) event).getPower();
        }
        eventDetails += '}';
        weatherEvents.add(eventDetails);


        for (Animal animal : animals) {
            animal.reactToEvent(event.toString());
            notifyObservers("Animal reacted: " + animal);
        }

        for (Plant plant : plants) {
            plant.reactToEvent(event.toString());
            notifyObservers("Animal reacted: " + plant);
        }
    }


    public void printPopulation() {
        System.out.println("Populacja:");
        if (animals.isEmpty()) {
            System.out.println("Brak zwierzat.");
        } else {
            for (Animal animal : animals) {
                // Pobranie informacji o zwierzęciu i formatowanie wyjścia
                System.out.println(animal.getClass().getSimpleName() +
                        " at " + animal.getPosition().getX() + ", " + animal.getPosition().getY() +
                        "\n Health: " + animal.getHealth() +
                        "\n Energy: " + animal.getEnergy() + "\n");
            }
        }

        System.out.println("Rośliny:");
        if (plants.isEmpty()) {
            System.out.println("Brak roślin.");
        } else {
            for (Plant plant : plants) {
                System.out.println(plant.getClass().getSimpleName() +
                        " at " + plant.getPosition().getX() + ", " + plant.getPosition().getY() +
                        "\n Health: " + plant.getHealth() +
                        "\n Hydration: " + plant.getHydration() + "\n");
            }
        }
    }
    public void printEnvironmentData() {
        System.out.println("Dane srodowiska:");
        System.out.println("Poziom wody: " + environment.getWaterLevel());
        System.out.println("Poziom swiatla: " + environment.getLightLevel());
    }
    public void printWeatherEvents() {
        if (weatherEvents.isEmpty()) {
            System.out.println("Brak zapisanych zdarzen pogodowych.");
        } else {
            System.out.println("Dotychczasowe zdarzenia pogodowe:");
            for (String event : weatherEvents) {
                System.out.println(event);
            }
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

            writer.append("Plant,Health,Hydration,GrowthStage,Position,Height/Diameter/IsBlooming\n");
            for (Plant plant : plants) {
                writer.append(plant.getClass().getSimpleName())
                        .append(',')
                        .append(String.valueOf(plant.getHealth()))
                        .append(',')
                        .append(String.valueOf(plant.getHydration()))
                        .append(',')
                        .append(plant.getGrowthStage())
                        .append(',')
                        .append(plant.getPosition().toString())
                        .append(',');

                if (plant instanceof Tree) {
                    writer.append(String.valueOf(((Tree) plant).getHeight()));
                } else if (plant instanceof Bush) {
                    writer.append(String.valueOf(((Bush) plant).getDiameter()));
                } else if (plant instanceof Flower) {
                    writer.append(String.valueOf(((Flower) plant).isBlooming()));
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
            for (String event : weatherEvents) {
                writer.append(event).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Symulacja ekosystemu lesnego: rozne gatunki roslin i zwierzat oddzialuja ze soba. Symulacja bedzie obejmowac srodowisko - plansze o z gory okreslonych wymiarach: 50 x 50.\n" +
                "Na poczatku symulacji, rozmieszczone zostana gatunki roslin, z poziomami potrzeb wodnych i swietlnych. Dodatkowo, na planszy pojawia sie rozrzucone zwierzeta, takie jak wilki, jelenie i ptaki. \n" +
                "Kazdy z tych organizmow bedzie posiadall okreslone cechy, takie jak tempo wzrostu, zdolnosc do przemieszczania sie oraz preferencje pokarmowe. \n" +
                "W trakcie symulacji beda generowane losowe zdarzenia, takie jak pozary lesne, susze lub deszcze, ktore będą wplywac na stan ekosystemu i dynamike populacji. \n" +
                "Symulacja bedzie odzwierciedlac zaleznosci pokarmowe, migracje zwierzat oraz interakcje pomiedzy gatunkami. \n" +
                "Dla kazdego etapu symulacji beda zbierane dane dotyczace licznosci populacji poszczegolnych gatunkow oraz stanu srodowiska.\n" +
                "Po zakonczeniu symulacji dane te zostana zapisane w formie pliku CSV w celu dalszej analizy.\n");

        EcosystemSimulation simulation = new EcosystemSimulation(50, 50);

        // Dodawanie obserwatora
//        Observer consoleObserver = new ConsoleObserver();
//        simulation.addObserver(consoleObserver);
//
//        simulation.addAnimal(new Wolf(100, 100, new Point(0, 0), "carnivore"));
//        simulation.addAnimal(new Deer(100, 100, new Point(10, 10), "herbivore"));
//        simulation.addAnimal(new Bird(100, 100, new Point(20, 20), "omnivore"));
//
//        simulation.addPlant(new Tree(90, 30, "rest", new Point(5, 15), 50));
//        simulation.addPlant(new Bush(90, 40, "growth", new Point(10, 25), 5));
//        simulation.addPlant(new Flower(90, 50, "blooming", new Point(12, 30), true));
//
//        simulation.simulate();
//
//        simulation.printPopulation();
//
//        simulation.saveSimulationDataToCSV("simulation_data.csv");
        Menu menu = new Menu(simulation);
        menu.displayMenu();
    }
}

