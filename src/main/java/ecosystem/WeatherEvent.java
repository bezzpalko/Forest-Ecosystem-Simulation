package ecosystem;

/**
 * Represents a weather event in the ecosystem.
 */
public interface WeatherEvent {

    /**
     * Applies the weather event to the specified environment.
     *
     * @param environment the environment to apply the weather event to
     */
    void apply(Environment environment);
}
