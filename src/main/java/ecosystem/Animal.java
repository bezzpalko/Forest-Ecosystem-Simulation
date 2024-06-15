package ecosystem;

/**
 * Represents an abstract class for an animal in the ecosystem.
 */
abstract class Animal {
    private int energy; // energy level of the animal
    private int health; // health of the animal
    private Point position; // position of the animal
    private String diet; // diet of the animal

    /**
     * Constructs an Animal with specified energy, health, position, and diet.
     *
     * @param energy the energy level of the animal
     * @param health the health of the animal
     * @param position the position of the animal
     * @param diet the diet of the animal
     */
    public Animal(int energy, int health, Point position, String diet) {
        this.energy = energy;
        this.health = health;
        this.position = position;
        this.diet = diet;
    }

    /**
     * Gets the energy level of the animal.
     *
     * @return the energy level of the animal
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Sets the energy level of the animal.
     *
     * @param energy the new energy level of the animal
     */
    public void setEnergy(int energy) {
        if (energy > 100) {
            this.energy = 100;
        } else {
            this.energy = energy;
        }
    }

    /**
     * Gets the health of the animal.
     *
     * @return the health of the animal
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the animal.
     *
     * @param health the new health of the animal
     */
    public void setHealth(int health) {
        if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
    }

    /**
     * Gets the position of the animal.
     *
     * @return the position of the animal
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Gets the diet of the animal.
     *
     * @return the diet of the animal
     */
    public String getDiet() {
        return diet;
    }

    /**
     * Sets the diet of the animal.
     *
     * @param diet the new diet of the animal
     */
    public void setDiet(String diet) {
        this.diet = diet;
    }

    /**
     * Updates the health and energy of the animal.
     *
     * @param healthChange the change in health
     * @param energyChange the change in energy
     */
    protected void updateHealthAndEnergy(int healthChange, int energyChange) {
        setHealth(getHealth() + healthChange);
        setEnergy(getEnergy() + energyChange);
    }

    /**
     * Displays a message after the animal reacts to an event.
     *
     * @param event the event the animal reacted to
     * @param healthChange the change in health due to the event
     * @param energyChange the change in energy due to the event
     */
    protected void reactToEventMessage(String event, int healthChange, int energyChange) {
        System.out.println(getClass().getSimpleName() + " reacted to " + event
                + ". Health changed by " + healthChange + ", Energy changed by " + energyChange);
    }

    /**
     * Displays a message after the animal interacts with another animal.
     *
     * @param other the animal the animal interacted with
     * @param energyChange the change in energy due to the interaction
     */
    protected void interactWithAnimalMessage(Animal other, int energyChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Energy changed by " + energyChange);
    }

    /**
     * Displays a message after the animal interacts with a plant.
     *
     * @param other the plant the animal interacted with
     * @param hydrationChange the change in hydration due to the interaction
     */
    protected void interactWithPlantMessage(Plant other, int hydrationChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Hydration changed by " + hydrationChange);
    }

    /**
     * Moves the animal.
     */
    public abstract void move();

    /**
     * Reacts to an event.
     *
     * @param event the event to react to
     */
    public abstract void reactToEvent(String event);

    /**
     * Interacts with a plant.
     *
     * @param other the plant to interact with
     */
    public abstract void interactWithPlant(Plant other);

    /**
     * Interacts with another animal.
     *
     * @param animal the animal to interact with
     */
    public abstract void interactWithAnimal(Animal animal);
}
