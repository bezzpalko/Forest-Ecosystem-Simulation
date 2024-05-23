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
    //moving
    public abstract void move();

    //reacting to random events
    public abstract void reactToEvent(String event);

    //interacting with other animals
    public abstract void interact(Animal other);
}