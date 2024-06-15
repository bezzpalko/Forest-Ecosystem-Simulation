package ecosystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the simulation of a forest ecosystem, including different species of plants and animals interacting with each other.
 */
public class EcosystemSimulation {
    final int width; // width of the ecosystem
    final int height; // height of the ecosystem
    private List<Animal> animals; // list of animals in the ecosystem
    private List<Plant> plants; // list of plants in the ecosystem
    final Environment environment; // the environment of the ecosystem
    private List<String> weatherEvents; // list of weather events that occurred
    private Scanner scanner; // scanner for user input

    /**
     * Constructs an EcosystemSimulation with specified width and height.
     *
     * @param width the width of the ecosystem
     * @param height the height of the ecosystem
     */
    public EcosystemSimulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.environment = new Environment(100, 100); // example initial values of waterLevel and lightLevel
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.weatherEvents = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds an animal to the ecosystem.
     *
     * @param animal the animal to add
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    /**
     * Removes an animal from the ecosystem.
     *
     * @param animal the animal to remove
     */
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Checks and removes dead animals from the ecosystem.
     */
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

    /**
     * Checks and removes dead plants from the ecosystem.
     */
    public void checkAndRemoveDeadPlants() {
        List<Plant> plantsToRemove = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant.getHealth() <= 0 || plant.getHydration() <= 0) {
                plantsToRemove.add(plant);
            }
        }
        for (Plant plant : plantsToRemove) {
            removePlant(plant);
        }
    }

    /**
     * Adds a plant to the ecosystem.
     *
     * @param plant the plant to add
     */
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    /**
     * Removes a plant from the ecosystem.
     *
     * @param plant the plant to remove
     */
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    /**
     * Gets the list of animals in the ecosystem.
     *
     * @return the list of animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Gets the list of plants in the ecosystem.
     *
     * @return the list of plants
     */
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Gets the environment of the ecosystem.
     *
     * @return the environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Simulates the ecosystem by updating states, generating events, and checking/removing dead entities.
     */
    public void simulate() {
        updateStates();
        generateEvents();
        checkAndRemoveDeadAnimals();
        checkAndRemoveDeadPlants();
    }

    /**
     * Updates the states of animals and plants in the ecosystem.
     */
    private void updateStates() {
        for (Animal animal : animals) {
            animal.move();
        }

        for (Plant plant : plants) {
            plant.move();
        }
    }

    /**
     * Generates weather events and applies them to the environment.
     */
    public void generateEvents() {
        System.out.println("Select the type of weather event to call:");
        System.out.println("1. Drought");
        System.out.println("2. Rain");
        System.out.println("3. Storm");
        int eventType = scanner.nextInt();
        scanner.nextLine();

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
                return; // termination of the method if an invalid option is selected
        }

        applyEventToEnvironment(event);
    }

    /**
     * Applies a weather event to the environment and updates the states of animals and plants accordingly.
     *
     * @param event the weather event to apply
     */
    public void applyEventToEnvironment(WeatherEvent event) {
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
        }

        for (Plant plant : plants) {
            plant.reactToEvent(event.toString());
        }
    }

    /**
     * Prints the population of animals and plants in the ecosystem.
     */
    public void printPopulation() {
        System.out.println("Population:");
        if (animals.isEmpty()) {
            System.out.println("No animals.");
        } else {
            for (Animal animal : animals) {
                // downloading animal information and formatting the output
                System.out.println(animal.getClass().getSimpleName() +
                        " at " + animal.getPosition().getX() + ", " + animal.getPosition().getY() +
                        "\n Health: " + animal.getHealth() + "/100" +
                        "\n Energy: " + animal.getEnergy() + "/100\n");
            }
        }

        System.out.println("Plants:");
        if (plants.isEmpty()) {
            System.out.println("No plants.");
        } else {
            for (Plant plant : plants) {
                // downloading plant information and formatting the output
                System.out.println(plant.getClass().getSimpleName() +
                        " at " + plant.getPosition().getX() + ", " + plant.getPosition().getY() +
                        "\n Health: " + plant.getHealth() + "/100" +
                        "\n Hydration: " + plant.getHydration() + "/100\n");
            }
        }
    }

    /**
     * Prints the environmental data of the ecosystem.
     */
    public void printEnvironmentData() {
        System.out.println("Environmental data:");
        System.out.println("Water level: " + environment.getWaterLevel());
        System.out.println("Light level: " + environment.getLightLevel());
    }

    /**
     * Prints the list of weather events that occurred in the ecosystem.
     */
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

    /**
     * Saves the simulation data to a CSV file.
     *
     * @param filename the name of the file to save the data to
     */
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

    /**
     * Closes the scanner used for user input.
     */
    public void close() {
        scanner.close();
    }

    /**
     * The main method to run the ecosystem simulation.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Simulation of a forest ecosystem: different species of plants and animals interacting with each other. The simulation will consist of an environment - a board with predefined dimensions: 20 x 20.\n" +
                "In the simulation, you can add different types of plants and animals, change their location, and change weather conditions. \n" +
                "Plants and animals appear on the board with an initial level of health, energy, or other indicator of 100%. Also, at the beginning of the simulation, the level of water and light is 100% \n" +
                "When the feature level reaches zero, it means removing the plant or the animal." +
                "Different weather conditions affect the state of plants and animals, as well as the state of the environment. \n" +
                "For example, rainy weather increases the water level, which is important for plant hydration.\n" +
                "During the simulation, different animals and plants can interact with each other, which affects their energy and health. \n" +
                "For each stage of the simulation, data will be collected on the population sizes of the different species and on the state of the environment.’ + ‘At the end of the simulation, data will be collected. \n" +
                "At the end of the simulation these data will be saved as a CSV file for further analysis. \n");

        EcosystemSimulation simulation = new EcosystemSimulation(20, 20); // example initial values of width and height
        Menu menu = new Menu(simulation);
        menu.displayMenu();
    }
}