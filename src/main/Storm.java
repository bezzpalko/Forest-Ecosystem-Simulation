import java.util.Random;

public class Storm {
    private int power;
    private Random random;

    public Storm(int power) {
        this.power = power;
        this.random = new Random();
    }

    public void place(Environment environment) {
        environment.applyStorm(power);
    }
}