
import java.util.Random;
public class Drought {
    private int strength;
    private Random random;

    public Drought(int strength) {
        this.strength = strength;
        this.random = new Random();
    }

    public void dry(Environment environment) {
        environment.applyDrought(strength);
    }
    public int getStrength() {
        return strength;
    }
}