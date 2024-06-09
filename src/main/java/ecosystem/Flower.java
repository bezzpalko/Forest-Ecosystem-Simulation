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
        int healthChange = 0;
        int hydrationChange = 0;

        if (event.contains("Drought")) {
            healthChange = -15;
            hydrationChange = -20;
        } else if (event.contains("Rain")) {
            healthChange = +10;
            hydrationChange = +15;
        } else if (event.contains("Storm")) {
            healthChange = -10;
            hydrationChange = +5;
        }
        updateHealthAndHydration(healthChange, hydrationChange);
        reactToEventMessage(event, healthChange, hydrationChange);
    }

    // interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        int hydrationChange = 0;
        if (other instanceof Tree) {
            hydrationChange = -10;
        } else if (other instanceof Bush) {
            hydrationChange = -5;
        }
        setHydration(getHydration() + hydrationChange);
        interactWithPlantMessage(other, hydrationChange);
    }

    // interaction with animals
    @Override
    public void interactWithAnimal(Animal other) {
        int healthChange = 0;
        if (other instanceof Wolf) {
            healthChange = 0;
        } else if (other instanceof Deer) {
            healthChange = -10;
        } else if (other instanceof Bird) {
            healthChange = -5;
        }
        setHealth(getHealth() + healthChange);
        interactWithAnimalMessage(other, healthChange);
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
