package ecosystem;

import ecosystem.Animal;

/**
 * Represents a Wolf in the ecosystem.
 */
class Wolf extends Animal {
    private boolean isPackLeader; // indicates if the wolf is the pack leader

    /**
     * Constructs a Wolf with specified energy, health, position, and diet.
     *
     * @param energy the energy level of the wolf
     * @param health the health of the wolf
     * @param position the position of the wolf
     * @param diet the diet of the wolf
     */
    public Wolf(int energy, int health, Point position, String diet) {
        super(energy, health, position, diet);
        this.isPackLeader = false;
    }

    /**
     * Checks if the wolf is the pack leader.
     *
     * @return true if the wolf is the pack leader, false otherwise
     */
    public boolean isPackLeader() {
        return isPackLeader;
    }

    /**
     * Sets whether the wolf is the pack leader.
     *
     * @param packLeader true if the wolf should be the pack leader, false otherwise
     */
    public void setPackLeader(boolean packLeader) {
        isPackLeader = packLeader;
    }

    /**
     * Moves the wolf by updating its position.
     * Ensures the wolf's position stays within the bounds of 0 to 20.
     */
    @Override
    public void move() {
        getPosition().translate(2, 2); // moving 2 units
        // ensure x and y are within the bounds of 0 to 20
        Point position = getPosition();
        if (position.getX() > 20) {
            position.setX(0);
        }
        if (position.getY() > 20) {
            position.setY(0);
        }
    }

    /**
     * Reacts to weather events by changing the wolf's health and energy.
     *
     * @param event the weather event to react to
     */
    @Override
    public void reactToEvent(String event) {
        int healthChange = 0;
        int energyChange = 0;

        if (event.contains("Drought")) {
            healthChange = -5;
            energyChange = -5;
        } else if (event.contains("Rain")) {
            healthChange = 5;
            energyChange = 5;
        } else if (event.contains("Storm")) {
            healthChange = -5;
        }
        updateHealthAndEnergy(healthChange, energyChange);
        reactToEventMessage(event, healthChange, energyChange);
    }

    /**
     * Interacts with another animal, potentially changing the wolf's energy.
     *
     * @param other the animal to interact with
     */
    @Override
    public void interactWithAnimal(Animal other) {
        int energyChange = 0;
        if (other instanceof Deer) {
            energyChange = 10;
        } else if (other instanceof Bird) {
            energyChange = 5;
        }
        setEnergy(getEnergy() + energyChange);
        interactWithAnimalMessage(other, energyChange);
    }

    /**
     * Interacts with a plant, potentially changing the wolf's health.
     *
     * @param other the plant to interact with
     */
    @Override
    public void interactWithPlant(Plant other) {
        int healthChange = 0;
        if (other instanceof Tree) {
            healthChange = 0;
        } else if (other instanceof Bush) {
            healthChange = 5;
        } else if (other instanceof Flower) {
            healthChange = 1;
        }
        setHealth(getHealth() + healthChange);
        interactWithPlantMessage(other, healthChange);
    }
}