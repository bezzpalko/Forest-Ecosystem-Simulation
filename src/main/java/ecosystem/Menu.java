package ecosystem;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
public class Menu {
    private EcosystemSimulation simulation;
    private Scanner scanner;

    public Menu(EcosystemSimulation simulation) {
        this.simulation = simulation;
        this.scanner = new Scanner(System.in);

    }

    public void displayMenu() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    addPlant();
                    break;
                case 3:
                    simulation.generateEvents();
                    break;
                case 4:
                    moveAnimals();
                    break;
                case 5:
                    movePlants();
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
                    System.out.println("Zakonczenie programu.");
                    return;
                default:
                    System.out.println("Nieprawidlowa opcja.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Dodaj zwierze");
        System.out.println("2. Dodaj roślinę");
        System.out.println("3. Dodaj dodatkowe zdarzenie pogodowe");
        System.out.println("4. Dodaj dodatkowy ruch zwierzat");
        System.out.println("5. Przesuń rośliny");
        System.out.println("6. Usun zwierze");
        System.out.println("7. Usuń roślinę");
        System.out.println("8. Wykonaj podstawowy krok symulacji (ruch zwierzat i zdarzenie pogodowe)");
        System.out.println("9. Wyswietl populacje");
        System.out.println("10. Wyswietl liste zdarzen pogodowych");
        System.out.println("11. Wyswietl dane srodowiska");
        System.out.println("12. Zapisz dane do CSV");
        System.out.println("13. Wyjscie z symulacji");
        System.out.print("Wybierz opcje: ");
    }


    private void addAnimal() {
        System.out.println("Wybierz typ zwierzecia:");
        System.out.println("1. Wolf");
        System.out.println("2. Deer");
        System.out.println("3. Bird");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Random random = new Random();
        int x = random.nextInt(50);
        int y = random.nextInt(50);

        Animal animal;
        switch (choice) {
            case 1:
                animal = new Wolf(40, 30, new Point(x, y), "miesozerny");
                break;
            case 2:
                animal = new Deer(50, 40, new Point(x, y), "roslinozerny");
                break;
            case 3:
                animal = new Bird(20, 20, new Point(x, y), "wszystkozerny");
                break;
            default:
                System.out.println("Nieprawidlowa opcja.");
                return;
        }
        simulation.addAnimal(animal);
    }
    private void moveAnimals() {
        for (Animal animal : simulation.getAnimals()) {
            animal.move();
        }
        System.out.println("Zwierzeta wykonaly dodatkowy ruch.");
    }

    private void removeAnimal() {
        System.out.print("Podaj indeks zwierzecia:");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<Animal> animalList = simulation.getAnimals();

        if (index >= 0 && index < animalList.size()) {
            Animal animal = animalList.get(index);
            simulation.removeAnimal(animal);
            System.out.println("Zwierze zostalo usuniete.");
        } else {
            System.out.println("Nieprawidlowy indeks.");
        }
    }

    private void addPlant() {
        System.out.println("Wybierz typ rośliny:");
        System.out.println("1. Tree");
        System.out.println("2. Bush");
        System.out.println("3. Flower");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Random random = new Random();
        int x = random.nextInt(50);
        int y = random.nextInt(50);

        Plant plant;
        switch (choice) {
            case 1:
                plant = new Tree(90, 30, "rest", new Point(x, y), 50);
                break;
            case 2:
                plant = new Bush(90, 40, "growth", new Point(x, y), 5);
                break;
            case 3:
                plant = new Flower(90, 50, "blooming", new Point(x, y), true);
                break;
            default:
                System.out.println("Nieprawidlowa opcja.");
                return;
        }
        simulation.addPlant(plant);
    }

    private void movePlants() {
        for (Plant plant : simulation.getPlants()) {
            plant.move();
        }
        System.out.println("The plants have been moved.");
    }

    private void removePlant() {
        System.out.print("Give the index of the plant: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<Plant> plantList = simulation.getPlants();

        if (index >= 0 && index < plantList.size()) {
            Plant plant = plantList.get(index);
            simulation.removePlant(plant);
            System.out.println("The plant has been removed.");
        } else {
            System.out.println("Incorrect index.");
        }
    }

    private void saveSimulationDataToCSV() {
        System.out.print("Podaj nazwe pliku do zapisania danych: ");
        String filename = scanner.nextLine();
        simulation.saveSimulationDataToCSV(filename);
        System.out.println("Dane zapisane do pliku " + filename);
    }
}