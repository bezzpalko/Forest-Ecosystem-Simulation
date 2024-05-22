
class Environment {
    private int waterLevel;
    private int lightLevel;

    public Environment(int waterLevel, int lightLevel) {
        this.waterLevel = waterLevel;
        this.lightLevel = lightLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
    }

    public void applyDrought(int strength) {
        waterLevel = Math.max(0, waterLevel - strength);
    }

    public void applyRain(int amount) {
        waterLevel += amount;
    }

    public void applyStorm(int power) {
        if (power % 2 == 0) {
            lightLevel += power;
        } else {
            lightLevel = Math.max(0, lightLevel - power);
        }
    }
}