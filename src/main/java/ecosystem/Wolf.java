package ecosystem;

import ecosystem.Animal;
import ecosystem.Bird;
import ecosystem.Deer;
import ecosystem.Point;

class Wolf extends Animal {
    private boolean isPackLeader;

    public Wolf(int energy, int health, Point position, String diet) {
        super(energy, health, position, diet);
        this.isPackLeader = false;
    }

    public boolean isPackLeader() {
        return isPackLeader;
    }

    public void setPackLeader(boolean packLeader) {
        isPackLeader = packLeader;
    }

    @Override
    public void move() {
        getPosition().translate(2, 2);

        // Ensure x and y are within the bounds of 0 to 50
        Point position = getPosition();
        if (position.getX() > 50) {
            position.setX(0);
        }
        if (position.getY() > 50) {
            position.setY(0);
        }
    }

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

    @Override
    public void interactWithAnimal(Animal other) {
        int energyChange = 0;
        if (other instanceof Deer) {
            energyChange = 10;
        } else if (other instanceof Bird) {
            energyChange = 5;
        }
        setEnergy(getEnergy() + energyChange);
        interactWithAnimalMessage(other, energyChange);
    }

    // interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        int healthChange = 0;
        if (other instanceof Tree) {
            healthChange = 0;
        } else if (other instanceof Bush) {
            healthChange = +5;
        } else if (other instanceof Flower) {
            healthChange = +1;
        }
        setHealth(getHealth() + healthChange);
        interactWithPlantMessage(other, healthChange);
    }
}