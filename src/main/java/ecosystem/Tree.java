package ecosystem;

import ecosystem.Plant;

public class Tree extends Plant {
    private int height; // tree height

    public Tree(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.height = 30;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    // response to weather conditions
    @Override
    public void reactToEvent(String event) {
        int healthChange = 0;
        int hydrationChange = 0;

        if (event.contains("Drought")) {
            healthChange = -10;
            hydrationChange = -15;
        } else if (event.contains("Rain")) {
            healthChange = +5;
            hydrationChange = +15;
        } else if (event.contains("Storm")) {
            healthChange = -5;
            hydrationChange = +5;
        }
        updateHealthAndHydration(healthChange, hydrationChange);
        reactToEventMessage(event, healthChange, hydrationChange);
    }

    // interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        int hydrationChange = 0;
        if (other instanceof Bush) {
            hydrationChange = -5;
        } else if (other instanceof Flower) {
            hydrationChange = -1;
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
            healthChange = -5;
        } else if (other instanceof Bird) {
            healthChange = -1;
        }
        setHealth(getHealth() + healthChange);
        interactWithAnimalMessage(other, healthChange);
    }

    //moving
    @Override
    public void move() {
        getPosition().translate(10, 5);
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
