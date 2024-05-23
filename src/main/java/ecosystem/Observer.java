package ecosystem;

public interface Observer {
    void update(String eventType, Object data);
}
//update w klasie pełniąca funkcję obserwatora
//metoda jest wywoływana, gdy obiekt, za którym "obserwator" podąża (obiekt ecosystem.Observable), zgłasza zmianę stanu