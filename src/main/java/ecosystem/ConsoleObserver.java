package ecosystem;

import java.util.Observer;
import java.util.Observable;

public class ConsoleObserver implements Observer { //implementation of ecosystem.Observer, defines what should happen when an observable object (ecosystem.Observable) reports an event
    @Override
    public void update(Observable o, Object arg) { //wyświetla komunikat, służy do logowanie/monitorowanie zdarzeń w sposób tekstowy.
        System.out.println("Event: " + arg);
    }
}