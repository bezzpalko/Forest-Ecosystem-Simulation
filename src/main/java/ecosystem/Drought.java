package ecosystem;//
//import java.util.Random;
//public class ecosystem.Drought {
//    private int strength;
//    private Random random;
//
//    public ecosystem.Drought() {
//        this.random = new Random();
//        this.strength = random.nextInt(10) + 1;
//    }
//
//    public void dry(Environment environment) {
//        environment.applyDrought(strength);
//    }
//    public int getStrength() {
//        return strength;
//    }
//}
import java.util.Random;

public class Drought implements WeatherEvent {
    private int strength;
    private static Random random = new Random();

    public Drought() {
        this.strength = random.nextInt(10) + 1;
    }

    @Override
    public void apply(Environment environment) {
        environment.applyDrought(strength);
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return "ecosystem.Drought{" + "strength=" + strength + '}';
    }
}