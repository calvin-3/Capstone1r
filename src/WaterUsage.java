import java.util.*;

// stores water usage data for various activities

public class WaterUsage {
        private double drinkingWater; // ounces
        private int showerTime; // minutes
        private int lightFlushes;
        private int heavyFlushes;
        private String laundryUsage; // low, medium, high
        private int handWashUses;
        private int dishWashUses;
        private String date;

        private static final double LIGHT_FLUSH_GALLONS = 1.0;
        private static final double HEAVY_FLUSH_GALLONS = 5.0;

        public WaterUsage() {
            this.date = new Date().toString(); // Capture date
        }

        // Setters for different types of water usage
        public void setDrinkingWater(double ounces) {
            this.drinkingWater = ounces;
        }

        public void setShowerTime(int minutes) {
            this.showerTime = minutes;
        }

        public void setLightFlushes(int flushes) {
            this.lightFlushes = flushes; }

        public void setHeavyFlushes(int flushes) {
            this.heavyFlushes = flushes; }

        public void setLaundryUsage(String level) {
            this.laundryUsage = level;
        }

        public void setSinkUsage(int handWashes, int dishWashes) {
            this.handWashUses = handWashes;
            this.dishWashUses = dishWashes;
        }

    // Getters for reporting and calculations
    public double getDrinkingWater() {return drinkingWater; }
    public int getShowerTime() {return showerTime; }
    public int getLightFlushes() {return lightFlushes; }
    public int getHeavyFlushes() {return heavyFlushes; }
    public String getLaundryUsage() {return laundryUsage; }
    public int getHandWashUses() { return handWashUses; }
    public int getDishWashUses() { return dishWashUses; }
    public String getDate() { return date; }

    // Calculate total water usage in gallons
    public double calculateTotalWaterUsage() {
        double flushWater = (lightFlushes * LIGHT_FLUSH_GALLONS) + (heavyFlushes * HEAVY_FLUSH_GALLONS);
        // Additional calculations for showering, hand washing, etc., can be added here.
        return flushWater; // Returning only flush water for now.
    }

    // accumulate water usage (used when loading past data)
    public void addPreviousUsage(WaterUsage previous) {
        this.drinkingWater += previous.drinkingWater;
        this.showerTime += previous.showerTime;
        this.lightFlushes += previous.lightFlushes;
        this.heavyFlushes += previous.heavyFlushes;
        this.handWashUses += previous.handWashUses;
        this.dishWashUses += previous.dishWashUses;
    }
}
