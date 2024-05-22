
import java.util.Random;

public class Rain {
    private int water;
    private Random random;

    public Rain(int water) {
        this.water = water;
        this.random = new Random();
    }

    public void destroy(Environment environment) {
        environment.applyRain(water);
    }
}