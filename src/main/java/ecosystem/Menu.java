package ecosystem;

import java.util.Scanner;
import java.util.List;
import java.util.Random;

/**
 * Represents the menu for the ecosystem simulation, allowing user interaction to control the simulation.
 */
public class Menu {
    final EcosystemSimulation simulation; // the ecosystem simulation
    final Scanner scanner; // scanner for user input

    /**
     * Constructs a Menu with the specified simulation.
     *
     * @param simulation the ecosystem simulation to control
     */
    public Menu(EcosystemSimulation simulation) {
        this.simulation = simulation;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the menu and handles user input.
     */
    public void displayMenu() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    addPlant();
                    break;
                case 3:
                    simulation.generateEvents();
                    simulation.checkAndRemoveDeadAnimals();
                    simulation.checkAndRemoveDeadPlants();
                    break;
                case 4:
                    moveAnimals();
                    simulation.checkAndRemoveDeadAnimals();
                    simulation.checkAndRemoveDeadPlants();
                    break;
                case 5:
                    movePlants();
                    simulation.checkAndRemoveDeadAnimals();
                    simulation.checkAndRemoveDeadPlants();
                    break;
                case 6:
                    removeAnimal();
                    break;
                case 7:
                    removePlant();
                    break;
                case 8:
                    simulation.simulate();
                    break;
                case 9:
                    simulation.printPopulation();
                    break;
                case 10:
                    simulation.printWeatherEvents();
                    break;
                case 11:
                    simulation.printEnvironmentData();
                    break;
                case 12:
                    saveSimulationDataToCSV();
                    break;
                case 13:
                    System.out.println("End of the programme.");
                    return;
                default:
                    System.out.println("Wrong option.");
            }
        }
    }

    /**
     * Prints the menu options.
     */
    private void printMenu() {
        System.out.println("\n--- The menu ---");
        System.out.println("1. Add animal");
        System.out.println("2. Add plant");
        System.out.println("3. Add an additional weather event");
        System.out.println("4. Add additional animal movement");
        System.out.println("5. Add additional plant movement");
        System.out.println("6. Remove the animal");
        System.out.println("7. Remove the plant");
        System.out.println("8. Perform basic simulation step (animal movement/plant movement and weather event)");
        System.out.println("9. View Populations");
        System.out.println("10. View a list of weather events");
        System.out.println("11. View environment data");
        System.out.println("12. Save data to CSV");
        System.out.println("13. Exit from the simulation");
        System.out.print("Select options:");
    }

    /**
     * Adds an animal to the simulation based on user input.
     */
    private void addAnimal() {
        System.out.println("Select animal type:");
        System.out.println("1. Wolf");
        System.out.println("2. Deer");
        System.out.println("3. Bird");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Random random = new Random();
        int x = random.nextInt(20); // drawing the starting position of the animal
        int y = random.nextInt(20);

        Animal animal;
        switch (choice) {
            case 1:
                animal = new Wolf(100, 100, new Point(x, y), "carnivorous");
                break;
            case 2:
                animal = new Deer(100, 100, new Point(x, y), "herbivorous");
                break;
            case 3:
                animal = new Bird(100, 100, new Point(x, y), "omnivorous");
                break;
            default:
                System.out.println("Wrong option.");
                return;
        }
        simulation.addAnimal(animal);
    }

    /**
     * Moves all animals in the simulation.
     */
    private void moveAnimals() {
        for (Animal animal : simulation.getAnimals()) {
            animal.move();
        }
        System.out.println("The animals made an additional move.");
    }

    /**
     * Removes an animal from the simulation based on user input.
     */
    private void removeAnimal() {
        System.out.print("Enter the animal's index:");
        int index = scanner.nextInt();
        scanner.nextLine();

        List<Animal> animalList = simulation.getAnimals();

        if (index >= 0 && index < animalList.size()) {
            Animal animal = animalList.get(index);
            simulation.removeAnimal(animal);
            System.out.println("The animal has been removed.");
        } else {
            System.out.println("Wrong index.");
        }
    }

    /**
     * Adds a plant to the simulation based on user input.
     */
    private void addPlant() {
        System.out.println("Select the type of plant:");
        System.out.println("1. Tree");
        System.out.println("2. Bush");
        System.out.println("3. Flower");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Random random = new Random();
        int x = random.nextInt(20); // drawing the starting position of the plant
        int y = random.nextInt(20);

        Plant plant;
        switch (choice) {
            case 1:
                plant = new Tree(100, 100, "rest", new Point(x, y));
                break;
            case 2:
                plant = new Bush(100, 100, "growth", new Point(x, y));
                break;
            case 3:
                plant = new Flower(100, 100, "blooming", new Point(x, y));
                break;
            default:
                System.out.println("Wrong option.");
                return;
        }
        simulation.addPlant(plant);
    }

    /**
     * Moves all plants in the simulation.
     */
    private void movePlants() {
        for (Plant plant : simulation.getPlants()) {
            plant.move();
        }
        System.out.println("The plants have been moved.");
    }

    /**
     * Removes a plant from the simulation based on user input.
     */
    private void removePlant() {
        System.out.print("Give the index of the plant: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        List<Plant> plantList = simulation.getPlants();

        if (index >= 0 && index < plantList.size()) {
            Plant plant = plantList.get(index);
            simulation.removePlant(plant);
            System.out.println("The plant has been removed.");
        } else {
            System.out.println("Wrong index.");
        }
    }

    /**
     * Saves the simulation data to a CSV file based on user input.
     */
    private void saveSimulationDataToCSV() {
        System.out.print("Specify the name of the file to save the data: ");
        String filename = scanner.nextLine();
        simulation.saveSimulationDataToCSV(filename);
        System.out.println("Data saved to file " + filename);
    }
}