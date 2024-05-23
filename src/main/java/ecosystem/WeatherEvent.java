package ecosystem;

import ecosystem.Environment;

public interface WeatherEvent {
    void apply(Environment environment);
}