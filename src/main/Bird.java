class Bird extends Animal {
    private int wingspan;

    public Bird(int energy, int health, Point position, String diet) {
        super(energy, health, position, diet);
        this.wingspan = 20;
    }

    public int getWingspan() {
        return wingspan;
    }

    public void setWingspan(int wingspan) {
        this.wingspan = wingspan;
    }

    @Override
    public void move() {
        getPosition().translate(0, 10);  // Birds move 10 units up
    }

    @Override
    public void reactToEvent(String event) {
        switch (event) {
            case "drought":
                setEnergy(getEnergy() - 5);
                break;
            case "rain":
                setEnergy(getEnergy() + 5);
                break;
            case "storm":
                setHealth(getHealth() - 10);
                break;
        }
    }

    @Override
    public void interact(Animal other) {
        if (other instanceof Wolf) {
            setHealth(getHealth() - 10);  // Example: Bird is scared by Wolf
        } else if (other instanceof Deer) {
            setEnergy(getEnergy() + 2);  // Example: Bird helps Deer find food
        }
    }
}
