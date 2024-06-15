package ecosystem;

import ecosystem.Plant;

/**
 * Represents a Bush in the ecosystem.
 */
class Bush extends Plant {
    private int diameter; // bush diameter

    /**
     * Constructs a Bush with specified health, hydration, growth stage, and position.
     *
     * @param health the health of the bush
     * @param hydration the hydration level of the bush
     * @param growthStage the growth stage of the bush
     * @param position the position of the bush
     */
    public Bush(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.diameter = 15;
    }

    /**
     * Gets the diameter of the bush.
     *
     * @return the diameter of the bush
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * Sets the diameter of the bush.
     *
     * @param diameter the new diameter of the bush
     */
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    /**
     * Reacts to weather conditions by changing the bush's health and hydration.
     *
     * @param event the weather event to react to
     */
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

    /**
     * Interacts with another plant, potentially changing the bush's hydration.
     *
     * @param other the plant to interact with
     */
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

    /**
     * Interacts with an animal, potentially changing the bush's health.
     *
     * @param other the animal to interact with
     */
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

    /**
     * Moves the bush by updating its position.
     * Ensures the bush's position stays within the bounds of 0 to 20.
     */
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
