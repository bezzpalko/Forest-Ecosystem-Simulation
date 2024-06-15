package ecosystem;

/**
 * Represents the environment in the ecosystem, including water and light levels.
 */
class Environment {
    private int waterLevel; // water level in the environment
    private int lightLevel; // light level in the environment

    /**
     * Constructs an Environment with specified water and light levels.
     *
     * @param waterLevel the initial water level
     * @param lightLevel the initial light level
     */
    public Environment(int waterLevel, int lightLevel) {
        this.waterLevel = waterLevel;
        this.lightLevel = lightLevel;
    }

    /**
     * Gets the water level of the environment.
     *
     * @return the water level
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * Sets the water level of the environment.
     *
     * @param waterLevel the new water level
     */
    public void setWaterLevel(int waterLevel) {
        if (waterLevel > 100) {
            this.waterLevel = 100;
        } else {
            this.waterLevel = waterLevel;
        }
    }

    /**
     * Gets the light level of the environment.
     *
     * @return the light level
     */
    public int getLightLevel() {
        return lightLevel;
    }

    /**
     * Sets the light level of the environment.
     *
     * @param lightLevel the new light level
     */
    public void setLightLevel(int lightLevel) {
        if (lightLevel > 100) {
            this.lightLevel = 100;
        } else {
            this.lightLevel = lightLevel;
        }
    }

    /**
     * Applies the effect of a drought to the environment by decreasing the water level.
     *
     * @param strength the strength of the drought
     */
    public void applyDrought(int strength) {
        waterLevel = Math.max(0, waterLevel - strength); // waterLevel decreases by the strength value; it does not drop below zero
    }

    /**
     * Applies the effect of rain to the environment by increasing the water level.
     *
     * @param amount the amount of rain
     */
    public void applyRain(int amount) {
        setWaterLevel(getWaterLevel() + amount);
    }

    /**
     * Applies the effect of a storm to the environment by modifying the light level.
     * If the power is even, the light level increases by the power value.
     * If the power is odd, the light level decreases by the power value but does not fall below zero.
     *
     * @param power the power of the storm
     */
    public void applyStorm(int power) {
        if (power % 2 == 0) { // when power is even, lightLevel increases by the value of power
            lightLevel += power;
        } else {
            lightLevel = Math.max(0, lightLevel - power); // when power is odd, lightLevel decreases by the value of power; does not fall below zero
        }
    }
}