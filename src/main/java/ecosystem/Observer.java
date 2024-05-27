package ecosystem;

public interface Observer {
    void update(String eventType, Object data);
}
//update in a class that acts as an observer
//method is called when the object that the ‘observer’ follows (ecosystem.Observable object) reports a change of state