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
        if (event.contains("Drought")) {
            setHealth(getHealth() - 10);
            setEnergy(getEnergy() - 10);
        } else if (event.contains("Rain")) {
            setHealth(getHealth() + 5);
            setEnergy(getEnergy() + 5);
        } else if (event.contains("Storm")) {
            setHealth(getHealth() - 5);
        }
    }

    @Override
    public void interact(Animal other) {
        if (other instanceof Deer) {
            setEnergy(getEnergy() + 10);  // Example: ecosystem.Wolf eats the ecosystem.Deer
        } else if (other instanceof Bird) {
            setEnergy(getEnergy() + 5);  // Example: ecosystem.Wolf scares the ecosystem.Bird away
        }
    }
}