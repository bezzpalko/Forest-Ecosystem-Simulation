package ecosystem;

import ecosystem.Plant;

public class Flower extends Plant {
    private boolean isBlooming; // is the flower blooming

    public Flower(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.isBlooming = true;
    }

    public boolean isBlooming() {
        return isBlooming;
    }
    public void setBlooming(boolean isBlooming) {
        this.isBlooming = isBlooming;
    }

    // response to weather conditions
    @Override
    public void reactToEvent(String event) {
        if (event.contains("Drought")) {
            setHealth(getHealth() - 10);
            setHydration(getHydration() - 20);
        } else if (event.contains("Rain")) {
            setHealth(getHealth() + 10);
            setHydration(getHydration() + 20);
        } else if (event.contains("Storm")) {
            setHealth(getHealth() - 20);
        }
    }

    // interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        if (this.getPosition().getX() == other.getPosition().getX() &&
                this.getPosition().getY() == other.getPosition().getY()) {
            other.setHealth(other.getHealth() - 5); // Example of interaction: Flower is blooming, which may attract insects
        }
    }

    // interaction with animals
    @Override
    public void interactWithAnimal(Animal animal) {
        if (animal instanceof Bird) {
            this.setHealth(this.getHealth() + 5); // Example of interaction: A bird pollinates a flower, increasing its health
            animal.setEnergy(animal.getEnergy() + 5); // The bird gains energy
        }
    }

    // moving
    @Override
    public void move() {
        getPosition().translate(5, 0);
        // Make sure that x and y are between 0 and 50
        Point position = getPosition();
        if (position.getX() > 50) {
            position.setX(0);
        }
        if (position.getY() > 50) {
            position.setY(0);
        }
    }
}
