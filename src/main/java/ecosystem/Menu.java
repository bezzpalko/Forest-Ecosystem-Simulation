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
                    simulation.generateEvents();
                    break;
                case 3:
                    moveAnimals();
                    break;
                case 4:
                    removeAnimal();
                    break;
                case 5:
                    simulation.simulate();
                    break;
                case 6:
                    simulation.printPopulation();
                    break;
                case 7:
                    simulation.printWeatherEvents();
                    break;
                case 8:
                    simulation.printEnvironmentData();
                    break;
                case 9:
                    saveSimulationDataToCSV();
                    break;
                case 10:
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
        System.out.println("2. Dodaj dodatkowe zdarzenie pogodowe");
        System.out.println("3. Dodaj dodatkowy ruch zwierzat");
        System.out.println("4. Usun zwierze");
        System.out.println("5. Wykonaj podstawowy krok symulacji (ruch zwierzat i zdarzenie pogodowe");
        System.out.println("6. Wyswietl populacje");
        System.out.println("7. Wyswietl liste zdarzen pogodowych");
        System.out.println("8. Wyswietl dane srodowiska");
        System.out.println("9. Zapisz dane do CSV");
        System.out.println("10. Wyjscie z symulacji");
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
    private void saveSimulationDataToCSV() {
        System.out.print("Podaj nazwe pliku do zapisania danych: ");
        String filename = scanner.nextLine();
        simulation.saveSimulationDataToCSV(filename);
        System.out.println("Dane zapisane do pliku " + filename);
    }
}