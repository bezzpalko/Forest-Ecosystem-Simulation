package ecosystem;
import java.util.Random;

/**
 * Represents a Drought weather event in the ecosystem.
 */
public class Drought implements WeatherEvent {
    private int strength; // strength of the drought
    private static Random random = new Random();

    /**
     * Constructs a Drought with a random strength between 1 and 10.
     */
    public Drought() {
        this.strength = random.nextInt(10) + 1;
    }

    /**
     * Applies the drought effect to the environment.
     *
     * @param environment the environment to apply the drought to
     */
    @Override
    public void apply(Environment environment) {
        environment.applyDrought(strength);
    }

    /**
     * Gets the strength of the drought.
     *
     * @return the strength of the drought
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Returns a string representation of the drought.
     *
     * @return a string representation of the drought
     */
    @Override
    public String toString() {
        return "ecosystem.Drought{" + "strength=" + strength + '}';
    }
}
