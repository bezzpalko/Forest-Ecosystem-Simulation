package ecosystem;

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
        if (waterLevel > 100) {
            this.waterLevel = 100;
        } else {
            this.waterLevel = waterLevel;
        }
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(int lightLevel) {
        if (lightLevel > 100) {
            this.lightLevel = 100;
        } else {
            this.lightLevel = lightLevel;
        }
    }

    public void applyDrought(int strength) {
        waterLevel = Math.max(0, waterLevel - strength); //waterLevel decreases by the strength value; it does not drop below zero
    }
    public void applyRain(int amount) {
        setWaterLevel(getWaterLevel() + amount);
    }

    public void applyStorm(int power) {
        if (power % 2 == 0) { //when power - even, lightLevel increases by the value of power
            lightLevel += power;
        } else {
            lightLevel = Math.max(0, lightLevel - power); //when power - odd, lightLevel decreases by the value of power; does not fall below zero
        }
    }
}