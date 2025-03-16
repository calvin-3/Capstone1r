public class WaterUsageReport {
    private WaterUsage usage;

    public WaterUsageReport(WaterUsage usage) {
        this.usage = usage;
    }

    public void displayReport() {
        System.out.println("\n-----\uD83D\uDCA7 Water Usage Report \uD83D\uDCA7-----");
        System.out.println("Date: " + usage.getDate());
        System.out.println("Drinking Water: " + usage.getDrinkingWater() + " oz");
        System.out.println("Shower Time: " + usage.getShowerTime() + " minutes");
        System.out.println("Toilet Flushes: " + usage.getLightFlushes() + " light (1 gal each), " +
                usage.getHeavyFlushes() + "  heavy (5 gal each) ");
        System.out.println("Laundry: " + usage.getLaundryUsage() + " soil level");
        System.out.println("Hand Washes: " + usage.getHandWashUses());
        System.out.println("Dish Washes: " + usage.getDishWashUses());
        double totalWaterUsed = usage.calculateTotalWaterUsage();

        System.out.println("---------------------------------");
    }
}

