import java.util.Observer;
import java.util.Observable;

public class ConsoleObserver implements Observer { //implementacja Observer, definiuje, co ma się stać, gdy obserwowany obiekt (Observable) zgłosi zdarzenie
    @Override
    public void update(Observable o, Object arg) { //wyświetla komunikat, służy do logowanie/monitorowanie zdarzeń w sposób tekstowy.
        System.out.println("Event: " + arg);
    }
}