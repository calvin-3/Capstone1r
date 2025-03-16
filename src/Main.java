import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for username
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        User user = new User(username);

        // Load user's cumulative data
        WaterUsage totalUsage = DataHandler.getCumulativeUsage(username);
        System.out.println("Your total water usage so far:");
        System.out.println("Drinking Water: " + totalUsage.getDrinkingWater() + " oz");
        System.out.println("Shower Time: " + totalUsage.getShowerTime() + " minutes");
        System.out.println("Light Flushes: " + totalUsage.getLightFlushes());
        System.out.println("Heavy Flushes: " + totalUsage.getHeavyFlushes());
        System.out.println("Hand Washes: " + totalUsage.getHandWashUses());
        System.out.println("Dish Washes: " + totalUsage.getDishWashUses());

        // Ask if they want to review past records
        System.out.print(" Would you like to review your past water usage records? (yes/no): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            List<String> allData = DataHandler.loadData();
            DataHandler.reviewUserData(allData, 0, username, scanner); // Recursive method call
        }

        // Ask if they want to enter new data
        System.out.println("Would you like to log new water usage? (yes/no)");
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            WaterUsage newUsage = new WaterUsage();

            System.out.print("Enter drinking water consumption (in ounces): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            newUsage.setDrinkingWater(scanner.nextDouble());

            System.out.print("Enter shower duration (in minutes): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
            newUsage.setShowerTime(scanner.nextInt());

            System.out.print("Enter number of light flushes (low water usage - 1 gal each): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
            newUsage.setLightFlushes(scanner.nextInt());

            System.out.print("Enter number of heavy flushes (high water usage - 5 gal each): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
            newUsage.setHeavyFlushes(scanner.nextInt());

            scanner.nextLine(); // Consume leftover newline
            System.out.print("Did you do laundry today? (yes/no): ");
            String didLaundry = scanner.next().trim().toLowerCase();
            if (didLaundry.equals("yes")) {
                System.out.print("Enter laundry usage level (low, medium, high): ");
                newUsage.setLaundryUsage(scanner.nextLine());
            } else {
                newUsage.setLaundryUsage("none"); // No laundry today
            }

            /*
            System.out.print("Enter number of hand washes today: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
            int handWashes = scanner.nextInt();

            System.out.print("Enter number of dish washes today: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
            int dishWashes = scanner.nextInt();

            newUsage.setSinkUsage(handWashes, dishWashes);
            */

            // Add new usage to the cumulative total
            totalUsage.addPreviousUsage(newUsage);

            // Store user data
            DataHandler.storeUsageData(user, newUsage);

            // Generate report
            WaterUsageReport report = new WaterUsageReport(totalUsage);
            report.displayReport();
        } else {
            System.out.println("No new data recorded.");
    }
        scanner.close();
    }
}