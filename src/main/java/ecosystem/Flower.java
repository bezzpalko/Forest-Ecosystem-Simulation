package ecosystem;

import ecosystem.Plant;

/**
 * Represents a Flower in the ecosystem.
 */
class Flower extends Plant {
    private boolean isBlooming; // indicates if the flower is blooming

    /**
     * Constructs a Flower with specified health, hydration, growth stage, and position.
     *
     * @param health the health of the flower
     * @param hydration the hydration level of the flower
     * @param growthStage the growth stage of the flower
     * @param position the position of the flower
     */
    public Flower(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.isBlooming = true;
    }

    /**
     * Checks if the flower is blooming.
     *
     * @return true if the flower is blooming, false otherwise
     */
    public boolean isBlooming() {
        return isBlooming;
    }

    /**
     * Sets the blooming state of the flower.
     *
     * @param isBlooming true if the flower should be blooming, false otherwise
     */
    public void setBlooming(boolean isBlooming) {
        this.isBlooming = isBlooming;
    }

    /**
     * Reacts to weather conditions by changing the flower's health and hydration.
     *
     * @param event the weather event to react to
     */
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

    /**
     * Interacts with another plant, potentially changing the flower's hydration.
     *
     * @param other the plant to interact with
     */
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

    /**
     * Interacts with an animal, potentially changing the flower's health.
     *
     * @param other the animal to interact with
     */
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

    /**
     * Moves the flower by updating its position.
     * Ensures the flower's position stays within the bounds of 0 to 20.
     */
    @Override
    public void move() {
        getPosition().translate(5, 0);
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