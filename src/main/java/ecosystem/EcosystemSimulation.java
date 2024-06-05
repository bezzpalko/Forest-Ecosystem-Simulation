package ecosystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
//import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
public class EcosystemSimulation extends Observable {
    final int width;
    final int height;
    private List<Animal> animals;
    private List<Plant> plants;
    final Environment environment; //na final
    private List<String> weatherEvents;
    private Random random;
    private Scanner scanner;


    public EcosystemSimulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.environment = new Environment(100, 100); // example initial values
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
    public void checkAndRemoveDeadAnimals() {
        List<Animal> animalsToRemove = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getHealth() <= 0 || animal.getEnergy() <= 0) {
                animalsToRemove.add(animal);
            }
        }
        for (Animal animal : animalsToRemove) {
            removeAnimal(animal);
        }
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

    public void simulate() { // update, generate events, set changes
        updateStates();
        generateEvents();
        checkAndRemoveDeadAnimals();
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
        System.out.println("Select the type of weather event to call:");
        System.out.println("1. Drought");
        System.out.println("2. Rain");
        System.out.println("3. Storm");
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
                System.out.println("Incorrect selection, please select number 1 to 3.");
                return; // Termination of the method if an invalid option is selected
        }

        applyEventToEnvironment(event);
    }

    public void applyEventToEnvironment(WeatherEvent event) { //giving the event back to the environment
        event.apply(environment);


        String eventDetails = "Event: " + event.getClass().getSimpleName() + " {";
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
        System.out.println("Population:");
        if (animals.isEmpty()) {
            System.out.println("No animals.");
        } else {
            for (Animal animal : animals) {
                // Downloading animal information and formatting the output
                System.out.println(animal.getClass().getSimpleName() +
                        " at " + animal.getPosition().getX() + ", " + animal.getPosition().getY() +
                        "\n Health: " + animal.getHealth() +
                        "\n Energy: " + animal.getEnergy() + "\n");
            }
        }

        System.out.println("Plants:");
        if (plants.isEmpty()) {
            System.out.println("No plants.");
        } else {
            for (Plant plant : plants) {
                // Downloading plant information and formatting the output
                System.out.println(plant.getClass().getSimpleName() +
                        " at " + plant.getPosition().getX() + ", " + plant.getPosition().getY() +
                        "\n Health: " + plant.getHealth() +
                        "\n Hydration: " + plant.getHydration() + "\n");
            }
        }
    }
    public void printEnvironmentData() {
        System.out.println("Environmental data:");
        System.out.println("Water level: " + environment.getWaterLevel());
        System.out.println("Light level: " + environment.getLightLevel());
    }
    public void printWeatherEvents() {
        if (weatherEvents.isEmpty()) {
            System.out.println("No recorded weather events.");
        } else {
            System.out.println("Weather events to date:");
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
        System.out.println("Simulation of a forest ecosystem: different species of plants and animals interacting with each other. The simulation will consist of an environment - a board with predefined dimensions: 50 x 50.\n" +
                "At the start of the simulation, plant species will be distributed, with levels of water and light needs. In addition, scattered animals such as wolves, deer and birds appear on the board. \n" +
                "Each of these organisms will have certain characteristics, such as growth rate, ability to move and food preferences. \n" +
                "Random events such as forest fires, droughts or rains will be generated during the simulation, which will affect the state of the ecosystem and population dynamics. \n" +
                "The simulation will reflect food relationships, animal migrations and interactions between species. \n" +
                "For each stage of the simulation, data will be collected on the population sizes of the different species and on the state of the environment.’ + ‘At the end of the simulation, data will be collected. \n" +
                "At the end of the simulation these data will be saved as a CSV file for further analysis. \n");

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

