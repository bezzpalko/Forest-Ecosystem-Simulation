package ecosystem;
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
        return "ecosystem.Rain{" + "water=" + water + '}';
    }
}