package ecosystem;

import ecosystem.Animal;


class Deer extends Animal {
    private boolean hasAntlers;

    public Deer(int energy, int health, Point position, String diet) {
        super(energy, health, position, diet);
        this.hasAntlers = true;
    }

    public boolean hasAntlers() {
        return hasAntlers;
    }

    public void setHasAntlers(boolean hasAntlers) {
        this.hasAntlers = hasAntlers;
    }

    //moving
    @Override
    public void move() {
        getPosition().translate(4, 4);  //moving 4 units
        // Ensure x and y are within the bounds of 0 to 20
        Point position = getPosition();
        if (position.getX() > 20) {
            position.setX(0);
        }
        if (position.getY() > 20) {
            position.setY(0);
        }
    }

    @Override
    public void reactToEvent(String event) { //the impact of weather events on the animal's parameters
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

    //interaction with other animals
    @Override
    public void interactWithAnimal(Animal other) {
        int energyChange = 0;
        if (other instanceof Wolf) {
            energyChange = -10;
        } else if (other instanceof Bird) {
            energyChange = -5;
        }
        setEnergy(getEnergy() + energyChange);
        interactWithAnimalMessage(other, energyChange);
    }

    //interaction with other plants
    @Override
    public void interactWithPlant(Plant other) {
        int healthChange = 0;
        if (other instanceof Tree) {
            healthChange = 5;
        } else if (other instanceof Bush) {
            healthChange = 10;
        } else if (other instanceof Flower) {
            healthChange = 5;
        }
        setHealth(getHealth() + healthChange);
        interactWithPlantMessage(other, healthChange);
    }
}
