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

        setHealth(getHealth() + healthChange);
        setEnergy(getEnergy() + energyChange);

        reactToEventMessage(event, healthChange, energyChange);
    }

    private void reactToEventMessage(String event, int healthChange, int energyChange) {
        System.out.println(getClass().getSimpleName() + " reacted to " + event
                + ". Health changed by " + healthChange + ", Energy changed by " + energyChange);
    }

    @Override
    public void interact(Animal other) {
        int energyChange = 0;
        if (other instanceof Deer) {
            energyChange = 10;
            setEnergy(getEnergy() + energyChange); // Example: ecosystem.Wolf eats the ecosystem.Deer
        } else if (other instanceof Bird) {
            energyChange = 5;
            setEnergy(getEnergy() + energyChange); // Example: ecosystem.Wolf scares the ecosystem.Bird away
        }
        interactMessage(other, energyChange);
    }
    private void interactMessage(Animal other, int energyChange) {
        System.out.println(getClass().getSimpleName() + " interacted with " + other.getClass().getSimpleName()
                + ". Energy changed by " + energyChange);
    }
}