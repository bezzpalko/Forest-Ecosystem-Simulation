package ecosystem;

import ecosystem.Plant;

class Bush extends Plant {
    private int diameter; // bush diameter

    public Bush(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.diameter = 15;
    }

    public int getDiameter() {
        return diameter;
    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    // response to weather conditions
    @Override
    public void reactToEvent(String event) {
        int healthChange = 0;
        int hydrationChange = 0;

        if (event.contains("Drought")) {
            healthChange = -10;
            hydrationChange = -10;
        } else if (event.contains("Rain")) {
            healthChange = 5;
            hydrationChange = 10;
        } else if (event.contains("Storm")) {
            healthChange = -10;
            hydrationChange = 10;
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
        } else if (other instanceof Flower) {
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
            healthChange = -1;
        } else if (other instanceof Deer) {
            healthChange = -10;
        } else if (other instanceof Bird) {
            healthChange = -1;
        }
        setHealth(getHealth() + healthChange);
        interactWithAnimalMessage(other, healthChange);
    }

    // moving
    @Override
    public void move() {
        getPosition().translate(0, 10);
        // Make sure that x and y are between 0 and 20
        Point position = getPosition();
        if (position.getX() > 20) {
            position.setX(0);
        }
        if (position.getY() > 20) {
            position.setY(0);
        }
    }
}
