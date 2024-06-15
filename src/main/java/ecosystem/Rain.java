package ecosystem;

import java.util.Random;

/**
 * Represents a Rain weather event in the ecosystem.
 */
public class Rain implements WeatherEvent {
    private int water; // the amount of water from the rain
    private static Random random = new Random();

    /**
     * Constructs a Rain event with a random amount of water between 1 and 10.
     */
    public Rain() {
        this.water = random.nextInt(10) + 1;
    }

    /**
     * Applies the rain effect to the environment by increasing the water level.
     *
     * @param environment the environment to apply the rain to
     */
    @Override
    public void apply(Environment environment) {
        environment.applyRain(water);
    }

    /**
     * Gets the amount of water from the rain.
     *
     * @return the amount of water
     */
    public int getWater() {
        return water;
    }

    /**
     * Returns a string representation of the rain event.
     *
     * @return a string representation of the rain event
     */
    @Override
    public String toString() {
        return "ecosystem.Rain{" + "water=" + water + '}';
    }
}