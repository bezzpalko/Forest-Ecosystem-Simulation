package ecosystem;

import ecosystem.Plant;

/**
 * Represents a Tree in the ecosystem.
 */
class Tree extends Plant {
    private int height; // tree height

    /**
     * Constructs a Tree with specified health, hydration, growth stage, and position.
     *
     * @param health the health of the tree
     * @param hydration the hydration level of the tree
     * @param growthStage the growth stage of the tree
     * @param position the position of the tree
     */
    public Tree(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.height = 30;
    }

    /**
     * Gets the height of the tree.
     *
     * @return the height of the tree
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the tree.
     *
     * @param height the new height of the tree
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Reacts to weather conditions by changing the tree's health and hydration.
     *
     * @param event the weather event to react to
     */
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

    /**
     * Interacts with another plant, potentially changing the tree's hydration.
     *
     * @param other the plant to interact with
     */
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

    /**
     * Interacts with an animal, potentially changing the tree's health.
     *
     * @param other the animal to interact with
     */
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

    /**
     * Moves the tree by updating its position.
     * Ensures the tree's position stays within the bounds of 0 to 20.
     */
    @Override
    public void move() {
        getPosition().translate(10, 5);
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