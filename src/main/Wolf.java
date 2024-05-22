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
        getPosition().translate(3, 3);  // Wolves move 3 units
    }

    @Override
    public void reactToEvent(String event) {
        switch (event) {
            case "drought":
                setHealth(getHealth() - 5);
                break;
            case "rain":
                setEnergy(getEnergy() + 5);
                break;
            case "storm":
                setEnergy(getEnergy() - 5);
                setHealth(getHealth() - 5);
                break;
        }
    }

    @Override
    public void interact(Animal other) {
        if (other instanceof Deer) {
            setEnergy(getEnergy() + 10);  // Example: Wolf eats the Deer
        } else if (other instanceof Bird) {
            setEnergy(getEnergy() + 5);  // Example: Wolf scares the Bird away
        }
    }
}