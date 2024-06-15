package ecosystem;

import java.util.Random;

/**
 * Represents a Storm weather event in the ecosystem.
 */
public class Storm implements WeatherEvent {
    private int power; // the power of the storm
    private static Random random = new Random();

    /**
     * Constructs a Storm event with a random power between 1 and 10.
     */
    public Storm() {
        this.power = random.nextInt(10) + 1;
    }

    /**
     * Applies the storm effect to the environment by modifying the light level.
     *
     * @param environment the environment to apply the storm to
     */
    @Override
    public void apply(Environment environment) {
        environment.applyStorm(power);
    }

    /**
     * Gets the power of the storm.
     *
     * @return the power of the storm
     */
    public int getPower() {
        return power;
    }

    /**
     * Returns a string representation of the storm event.
     *
     * @return a string representation of the storm event
     */
    @Override
    public String toString() {
        return "ecosystem.Storm{" + "power=" + power + '}';
    }
}