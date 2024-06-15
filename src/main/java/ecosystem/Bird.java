package ecosystem;

import ecosystem.Animal;

/**
 * Represents a Bird in the ecosystem.
 */
class Bird extends Animal {
    private int wingspan; // wingspan of the bird

    /**
     * Constructs a Bird with specified energy, health, position, and diet.
     *
     * @param energy the energy level of the bird
     * @param health the health of the bird
     * @param position the position of the bird
     * @param diet the diet of the bird
     */
    public Bird(int energy, int health, Point position, String diet) {
        super(energy, health, position, diet);
        this.wingspan = 20;
    }

    /**
     * Gets the wingspan of the bird.
     *
     * @return the wingspan of the bird
     */
    public int getWingspan() {
        return wingspan;
    }

    /**
     * Sets the wingspan of the bird.
     *
     * @param wingspan the new wingspan of the bird
     */
    public void setWingspan(int wingspan) {
        this.wingspan = wingspan;
    }

    /**
     * Moves the bird by updating its position.
     * Ensures the bird's position stays within the bounds of 0 to 20.
     */
    @Override
    public void move() {
        getPosition().translate(1, 1); // moving 1 unit
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
     * Reacts to weather events by changing the bird's health and energy.
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
     * Interacts with another animal, potentially changing the bird's energy.
     *
     * @param other the animal to interact with
     */
    @Override
    public void interactWithAnimal(Animal other) {
        int energyChange = 0;
        if (other instanceof Deer) {
            energyChange = -5;
        } else if (other instanceof Wolf) {
            energyChange = -10;
        }
        setEnergy(getEnergy() + energyChange);
        interactWithAnimalMessage(other, energyChange);
    }

    /**
     * Interacts with a plant, potentially changing the bird's health.
     *
     * @param other the plant to interact with
     */
    @Override
    public void interactWithPlant(Plant other) {
        int healthChange = 0;
        if (other instanceof Tree) {
            healthChange = 1;
        } else if (other instanceof Bush) {
            healthChange = 5;
        } else if (other instanceof Flower) {
            healthChange = 5;
        }
        setHealth(getHealth() + healthChange);
        interactWithPlantMessage(other, healthChange);
    }
}
