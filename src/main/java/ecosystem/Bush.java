package ecosystem;

import ecosystem.Plant;

public class Bush extends Plant {
    private int diameter; // bush diameter

    public Bush(int health, int hydration, String growthStage, Point position, int diameter) {
        super(health, hydration, growthStage, position);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    // response to weather conditions
    @Override
    public void reactToEvent(String event) {
        switch (event) {
            case "drought":
                setHealth(getHealth() - 10);
                setHydration(getHydration() - 10);
                break;
            case "rain":
                setHealth(getHealth() + 10);
                setHydration(getHydration() + 10);
                break;
            case "storm":
                setHealth(getHealth() - 10);
                break;
        }
    }

    // interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        if (this.getPosition().getX() == other.getPosition().getX() &&
                this.getPosition().getY() == other.getPosition().getY()) {
            other.setHydration(other.getHydration() - 5); // Example of interaction: the root influences the hydration of other plants
        }
    }

    // interaction with animals
    public void interactWithAnimal(Animal animal) {
        if (animal instanceof Deer) {
            this.setHealth(this.getHealth() - 5); // Example of interaction: Deer eats bush leaves, reduces bush health
            animal.setEnergy(animal.getEnergy() + 5); // Deer gain energy
        }
    }

    // moving
    @Override
    public void move() {
        getPosition().translate(0, 10);
        // Make sure that x and y are between 0 and 50
        Point position = getPosition();
        if (position.getX() > 50) {
            position.setX(0);
        }
        if (position.getY() > 50) {
            position.setY(0);
        }
    }
}
