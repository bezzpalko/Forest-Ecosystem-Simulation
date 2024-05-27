package ecosystem;

import ecosystem.Plant;

public class Tree extends Plant {
    private int height; // tree height

    public Tree(int health, int hydration, String growthStage, Point position) {
        super(health, hydration, growthStage, position);
        this.height = 30;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    // response to weather conditions
    @Override
    public void reactToEvent(String event) {
        switch (event) {
            case "drought":
                setHealth(getHealth() - 5);
                setHydration(getHydration() - 5);
                break;
            case "rain":
                setHealth(getHealth() + 10);
                setHydration(getHydration() + 10);
                break;
            case "storm":
                setHealth(getHealth() - 5);
                break;
        }
    }

    // interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        if (this.getPosition().getX() == other.getPosition().getX() &&
                this.getPosition().getY() == other.getPosition().getY()) {
            other.setHealth(other.getHealth() - 10); // Example of interaction: Tree blocks light from other plants
        }
    }

    // interaction with animals
    @Override
    public void interactWithAnimal(Animal animal) {
        if (animal instanceof Deer) {
            this.setHealth(this.getHealth() - 10); // Example of interaction: Deer eats tree leaves, reduces tree health
            animal.setEnergy(animal.getEnergy() + 10); // Deer gain energy
        }
    }


    //moving
    @Override
    public void move() {
        getPosition().translate(10, 5);
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
