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
        if (energy > 100) {
            this.energy = 100;
        } else {
            this.energy = energy;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
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

    protected void updateHealthAndEnergy(int healthChange, int energyChange) { //method to update health and energy
        setHealth(getHealth() + healthChange);
        setEnergy(getEnergy() + energyChange);
    }

    protected void reactToEventMessage(String event, int healthChange, int energyChange) { //method to display messages after the event response
        System.out.println(getClass().getSimpleName() + " reacted to " + event
                + ". Health changed by " + healthChange + ", Energy changed by " + energyChange);
    }

    protected void interactWithAnimalMessage(Animal other, int energyChange) { //method to display messages after interaction with other animal
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Energy changed by " + energyChange);
    }

    protected void interactWithPlantMessage(Plant other, int hydrationChange) { //method to display messages after interaction with plant
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Hydration changed by " + hydrationChange);
    }

    //abstract methods
    public abstract void move();

    public abstract void reactToEvent(String event);

    public abstract void interactWithPlant(Plant other); // interaction with other plants

    public abstract void interactWithAnimal(Animal animal); // interaction with animals
}