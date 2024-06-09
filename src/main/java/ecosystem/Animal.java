package ecosystem;

abstract class Animal {

    private int energy;
    private int health;
    private Point position;
    private String diet;

    public Animal(int energy, int health, Point position, String diet) {
        this.energy = energy;
        this.health = health;
        this.position = position;
        this.diet = diet;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Point getPosition() {
        return position;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    // Method to update health and energy
    protected void updateHealthAndEnergy(int healthChange, int energyChange) {
        setHealth(getHealth() + healthChange);
        setEnergy(getEnergy() + energyChange);
    }

    //Method to display messages after the event response
    protected void reactToEventMessage(String event, int healthChange, int energyChange) {
        System.out.println(getClass().getSimpleName() + " reacted to " + event
                + ". Health changed by " + healthChange + ", Energy changed by " + energyChange);
    }

    //Method to display messages after interaction with animal
    protected void interactMessage(Animal other, int energyChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Energy changed by " + energyChange);
    }

    // Abstract methods
    public abstract void move();

    public abstract void reactToEvent(String event);

    public abstract void interact(Animal other);
}