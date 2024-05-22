//import java.util.Random;
//
//public class Storm {
//    private int power;
//    private Random random;
//
//    public Storm() {
//        this.random = new Random();
//        this.power = random.nextInt(10) + 1;
//    }
//
//    public void place(Environment environment) {
//        environment.applyStorm(power);
//    }
//
//    public int getPower() {
//        return power;
//    }
//    @Override
//    public String toString() {
//        return "Storm{" + "power=" + power + '}';
//    }
//}
import java.util.Random;

public class Storm implements WeatherEvent {
    private int power;
    private static Random random = new Random();

    public Storm() {
        this.power = random.nextInt(10) + 1;
    }

    @Override
    public void apply(Environment environment) {
        environment.applyStorm(power);
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Storm{" + "power=" + power + '}';
    }
}