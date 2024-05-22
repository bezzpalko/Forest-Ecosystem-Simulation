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
        getPosition().translate(5, 0);  // Deer move 5 units
    }

    @Override
    public void reactToEvent(String event) {
        switch (event) {
            case "drought":
                setHealth(getHealth() - 10);
                break;
            case "rain":
                setEnergy(getEnergy() + 10);
                break;
            case "storm":
                setHealth(getHealth() - 5);
                break;
        }
    }

    @Override
    public void interact(Animal other) {
        if (other instanceof Wolf) {
            // Logic for interaction with a Wolf
            setHealth(getHealth() - 10);  // Example: Deer gets injured
        } else if (other instanceof Bird) {
            // Logic for interaction with a Bird
            setEnergy(getEnergy() + 2);  // Example: Bird alerts Deer to danger
        }
    }
}
