package ecosystem;

import ecosystem.Animal;
import ecosystem.Bird;

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

    @Override
    public void move() {
        getPosition().translate(4, 4);  // ecosystem.Deer move 5 units

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
            setHealth(getHealth() - 5);
            setEnergy(getEnergy() - 5);
        } else if (event.contains("Rain")) {
            setHealth(getHealth() + 5);
            setEnergy(getEnergy() + 5);
        } else if (event.contains("Storm")) {
            setHealth(getHealth() - 5);
        }
    }

    @Override
    public void interact(Animal other) {
        if (other instanceof Wolf) {
            setHealth(getHealth() - 10);  // Example: ecosystem.Deer gets injured
        } else if (other instanceof Bird) {
            setEnergy(getEnergy() + 2);  // Example: ecosystem.Bird alerts ecosystem.Deer to danger
        }
    }
}
