package ecosystem;

/**
 * Represents an abstract class for a plant in the ecosystem.
 */
abstract class Plant {
    private int health; // plant health
    private int hydration; // moisture level of the plant
    private String growthStage; // plant growth stage (e.g. rest, growth, blooming)
    private Point position; // plant position

    /**
     * Constructs a Plant with specified health, hydration, growth stage, and position.
     *
     * @param health the health of the plant
     * @param hydration the moisture level of the plant
     * @param growthStage the growth stage of the plant (e.g. rest, growth, blooming)
     * @param position the position of the plant
     */
    public Plant (int health, int hydration, String growthStage, Point position) {
        this.health = health;
        this.hydration = hydration;
        this.growthStage = growthStage;
        this.position = position;
    }

    /**
     * Gets the health of the plant.
     *
     * @return the health of the plant
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the plant.
     *
     * @param health the new health of the plant
     */
    public void setHealth(int health) {
        if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
    }

    /**
     * Gets the hydration level of the plant.
     *
     * @return the hydration level of the plant
     */
    public int getHydration() {
        return hydration;
    }

    /**
     * Sets the hydration level of the plant.
     *
     * @param hydration the new hydration level of the plant
     */
    public void setHydration(int hydration) {
        if (hydration > 100) {
            this.hydration = 100;
        } else {
            this.hydration = hydration;
        }
    }

    /**
     * Gets the position of the plant.
     *
     * @return the position of the plant
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Gets the growth stage of the plant.
     *
     * @return the growth stage of the plant
     */
    public String getGrowthStage() {
        return growthStage;
    }

    /**
     * Sets the growth stage of the plant.
     *
     * @param growthStage the new growth stage of the plant
     */
    public void setGrowthStage(String growthStage) {
        this.growthStage = growthStage;
    }

    /**
     * Updates the health and hydration of the plant.
     *
     * @param healthChange the change in health
     * @param hydrationChange the change in hydration
     */
    protected void updateHealthAndHydration(int healthChange, int hydrationChange) {
        setHealth(getHealth() + healthChange);
        setHydration(getHydration() + hydrationChange);
    }

    /**
     * Displays a message after the plant reacts to an event.
     *
     * @param event the event the plant reacted to
     * @param healthChange the change in health due to the event
     * @param hydrationChange the change in hydration due to the event
     */
    protected void reactToEventMessage(String event, int healthChange, int hydrationChange) {
        System.out.println(getClass().getSimpleName() + " reacted to " + event
                + ". Health changed by " + healthChange + ", Hydration changed by " + hydrationChange);
    }

    /**
     * Displays a message after the plant interacts with an animal.
     *
     * @param other the animal the plant interacted with
     * @param healthChange the change in health due to the interaction
     */
    protected void interactWithAnimalMessage(Animal other, int healthChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Health changed by " + healthChange);
    }

    /**
     * Displays a message after the plant interacts with another plant.
     *
     * @param other the plant the plant interacted with
     * @param hydrationChange the change in hydration due to the interaction
     */
    protected void interactWithPlantMessage(Plant other, int hydrationChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Hydration changed by " + hydrationChange);
    }

    /**
     * Reacts to weather conditions.
     *
     * @param event the weather condition
     */
    public abstract void reactToEvent(String event);

    /**
     * Interacts with another plant.
     *
     * @param other the plant to interact with
     */
    public abstract void interactWithPlant(Plant other);

    /**
     * Interacts with an animal.
     *
     * @param animal the animal to interact with
     */
    public abstract void interactWithAnimal(Animal animal);

    /**
     * Moves the plant.
     */
    public abstract void move();
}
