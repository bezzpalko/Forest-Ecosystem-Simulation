import java.util.Observer;
import java.util.Observable;

//public class ConsoleObserver implements Observer {
//    @Override
//    public void update(String eventType, Object data) {
//        System.out.println("Event: " + eventType + ", Data: " + data);
//    }
//}
public class ConsoleObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Event: " + arg);
    }
}