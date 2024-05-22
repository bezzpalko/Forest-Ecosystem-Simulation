import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String eventType, Object data) { //przez listę obserwatorów: wywołuje update z arg, informując ich o zdarzeniu.
        for (Observer observer : observers) {
            observer.update(eventType, data);
        }
    }
}