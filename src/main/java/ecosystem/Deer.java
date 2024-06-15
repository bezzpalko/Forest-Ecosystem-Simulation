package ecosystem;

import ecosystem.Animal;

/**
 * Represents a Deer in the ecosystem.
 */
class Deer extends Animal {
    private boolean hasAntlers; // indicates if the deer has antlers

    /**
     * Constructs a Deer with specified energy, health, position, and diet.
     *
     * @param energy the energy level of the deer
     * @param health the health of the deer
     * @param position the position of the deer
     * @param diet the diet of the deer
     */
    public Deer(int energy, int health, Point position, String diet) {
        super(energy, health, position, diet);
        this.hasAntlers = true;
    }

    /**
     * Checks if the deer has antlers.
     *
     * @return true if the deer has antlers, false otherwise
     */
    public boolean hasAntlers() {
        return hasAntlers;
    }

    /**
     * Sets whether the deer has antlers.
     *
     * @param hasAntlers true if the deer has antlers, false otherwise
     */
    public void setHasAntlers(boolean hasAntlers) {
        this.hasAntlers = hasAntlers;
    }

    /**
     * Moves the deer by updating its position.
     * Ensures the deer's position stays within the bounds of 0 to 20.
     */
    @Override
    public void move() {
        getPosition().translate(4, 4); // moving 4 units
        // Ensure x and y are within the bounds of 0 to 20
        Point position = getPosition();
        if (position.getX() > 20) {
            position.setX(0);
        }
        if (position.getY() > 20) {
            position.setY(0);
        }
    }

    /**
     * Reacts to weather events by changing the deer's health and energy.
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
     * Interacts with another animal, potentially changing the deer's energy.
     *
     * @param other the animal to interact with
     */
    @Override
    public void interactWithAnimal(Animal other) {
        int energyChange = 0;
        if (other instanceof Wolf) {
            energyChange = -10;
        } else if (other instanceof Bird) {
            energyChange = -5;
        }
        setEnergy(getEnergy() + energyChange);
        interactWithAnimalMessage(other, energyChange);
    }

    /**
     * Interacts with a plant, potentially changing the deer's health.
     *
     * @param other the plant to interact with
     */
    @Override
    public void interactWithPlant(Plant other) {
        int healthChange = 0;
        if (other instanceof Tree) {
            healthChange = 5;
        } else if (other instanceof Bush) {
            healthChange = 10;
        } else if (other instanceof Flower) {
            healthChange = 5;
        }
        setHealth(getHealth() + healthChange);
        interactWithPlantMessage(other, healthChange);
    }
}