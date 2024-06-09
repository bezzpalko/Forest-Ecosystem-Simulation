package ecosystem;

abstract class Plant {
    private int health; // plant health
    private int hydration; // moisture level of the plant
    private String growthStage; // plant growth stage (e.g. rest, growth, blooming)
    private Point position; // plant position

    public Plant (int health, int hydration, String growthStage, Point position) {
        this.health = health;
        this.hydration = hydration;
        this.growthStage = growthStage;
        this.position = position;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHydration() {
        return hydration;
    }
    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public Point getPosition() {
        return position;
    }

    public String getGrowthStage() {
        return growthStage;
    }
    public void setGrowthStage(String growthStage) {
        this.growthStage = growthStage;
    }

    //Method to update health and hydration
    protected void updateHealthAndHydration(int healthChange, int hydrationChange) {
        setHealth(getHealth() + healthChange);
        setHydration(getHydration() + hydrationChange);
    }

    //Method to display messages after the event response
    protected void reactToEventMessage(String event, int healthChange, int hydrationChange) {
        System.out.println(getClass().getSimpleName() + " reacted to " + event
                + ". Health changed by " + healthChange + ", Hydration changed by " + hydrationChange);
    }

    //Method to display messages after interaction with an animal
    protected void interactWithAnimalMessage(Animal other, int healthChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Health changed by " + healthChange);
    }

    //Method to display messages after interaction with plant
    protected void interactWithPlantMessage(Plant other, int hydrationChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Hydration changed by " + hydrationChange);
    }

    //Abstract methods
    public abstract void reactToEvent(String event); // response to weather conditions

    public abstract void interactWithPlant(Plant other); // interaction with other plants

    public abstract void interactWithAnimal(Animal animal); // interaction with animals

    public abstract void move(); // moving
}
