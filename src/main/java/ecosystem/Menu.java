package ecosystem;

import java.util.Scanner;
import java.util.List;
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
                    removeAnimal();
                    break;
                case 3:
                    simulation.generateEvents();
                    break;
                case 4:
                    simulation.simulate();
                    break;
                case 5:
                    simulation.printPopulation();
                    break;
                case 6:
                    saveSimulationDataToCSV();
                    break;
                case 7:
                    simulation.printWeatherEvents();
                    break;
                case 8:
                    simulation.printEnvironmentData();
                    break;
                case 9:
                    printMenu();
                    break;
                case 10:
                    System.out.println("Zakończenie programu.");
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Dodaj zwierzę");
        System.out.println("2. Usuń zwierzę");
        System.out.println("3. Wywołaj zdarzenie pogodowe");
        System.out.println("4. Wykonaj krok symulacji");
        System.out.println("5. Wyświetl populację");
        System.out.println("6. Zapisz dane do CSV");
        System.out.println("7. Wyświetl log zdarzeń");
        System.out.println("8. Wyświetl dane środowiska");  // Nowa opcja
        System.out.println("9. Wyświetl menu ponownie");
        System.out.println("10. Wyjście");
        System.out.print("Wybierz opcję: ");
    }

    private void addAnimal() {
        System.out.println("Wybierz typ zwierzęcia do dodania:");
        System.out.println("1. Wolf");
        System.out.println("2. Deer");
        System.out.println("3. Bird");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Podaj współrzędne początkowe (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Animal animal;
        switch (choice) {
            case 1:
                animal = new Wolf(100, 100, new Point(x, y), "carnivore");
                break;
            case 2:
                animal = new Deer(100, 100, new Point(x, y), "herbivore");
                break;
            case 3:
                animal = new Bird(100, 100, new Point(x, y), "omnivore");
                break;
            default:
                System.out.println("Nieprawidłowa opcja.");
                return;
        }
        simulation.addAnimal(animal);
    }

    private void removeAnimal() {
        System.out.print("Podaj indeks zwierzęcia do usunięcia: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<Animal> animalList = simulation.getAnimals();

        if (index >= 0 && index < animalList.size()) {
            Animal animal = animalList.get(index);
            simulation.removeAnimal(animal); // Usunięcie zwierzęcia z symulacji
            System.out.println("Zwierzę zostało usunięte.");
        } else {
            System.out.println("Nieprawidłowy indeks.");
        }
    }
    private void saveSimulationDataToCSV() {
        System.out.print("Podaj nazwę pliku do zapisania danych: ");
        String filename = scanner.nextLine();
        simulation.saveSimulationDataToCSV(filename);
        System.out.println("Dane zapisane do pliku " + filename);
    }
}