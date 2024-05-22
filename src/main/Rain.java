//
//import java.util.Random;
//
//public class Rain {
//    private int water;
//    private Random random;
//    public Rain() {
//        this.random = new Random();
//        this.water = random.nextInt(10) + 1;
//    }
//    public void destroy(Environment environment) {
//        environment.applyRain(water);
//    }
//    public int getWater() {
//        return water;
//    }
//    @Override
//    public String toString() {
//        return "Rain{" + "water=" + water + '}';
//    }
//}
import java.util.Random;

public class Rain implements WeatherEvent {
    private int water;
    private static Random random = new Random();

    public Rain() {
        this.water = random.nextInt(10) + 1;
    }

    @Override
    public void apply(Environment environment) {
        environment.applyRain(water);
    }

    public int getWater() {
        return water;
    }

    @Override
    public String toString() {
        return "Rain{" + "water=" + water + '}';
    }
}