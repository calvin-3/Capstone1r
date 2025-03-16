import java.io.*;
import java.util.*;

// Handles reading and writing water usage data to a file
public class DataHandler {
    private static final String FILE_NAME = "water_usage.csv";

    public static void storeUsageData(User user, WaterUsage usage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.getName() + "," + usage.getDate() + "," +
                    usage.getDrinkingWater() + "," + usage.getShowerTime() + "," +
                    usage.getLightFlushes() + "," + usage.getHeavyFlushes() + "," +
                    usage.getLaundryUsage() + "," + usage.getHandWashUses() + "," +
                    usage.getDishWashUses());
            writer.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List <String> loadData() {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    public static WaterUsage getCumulativeUsage(String username) {
        List<String> allData = loadData();
        WaterUsage totalUsage = new WaterUsage(); // Start with an empty object

        for (String line : allData) {
            String[] entry = line.split(",");
            if (entry[0].equals(username)) {
                WaterUsage pastUsage = new WaterUsage();
                pastUsage.setDrinkingWater(Double.parseDouble(entry[2]));
                pastUsage.setShowerTime(Integer.parseInt(entry[3]));
                pastUsage.setLightFlushes(Integer.parseInt(entry[4]));
                pastUsage.setHeavyFlushes(Integer.parseInt(entry[5]));
                pastUsage.setLaundryUsage(entry[6]);
                pastUsage.setSinkUsage(Integer.parseInt(entry[7]), Integer.parseInt(entry[8]));

                // Add past data to total
                totalUsage.addPreviousUsage(pastUsage);
            }
        }
        return totalUsage;
    }

    // Recursive method to display user data one record at a time
    public static void reviewUserData(List<String> data, int index, String username, Scanner scanner) {
        if (index >= data.size()) {
            System.out.println("🏁 No more records found.");
            return;
        }

        String[] entry = data.get(index).split(",");
        if (entry[0].equals(username)) { // Check if entry belongs to the user
            System.out.println("\n💧 ---- Water Usage Record ---- 💧");
            System.out.println("📅 Date: " + entry[1]);
            System.out.println("🥤 Drinking Water: " + entry[2] + " oz");
            System.out.println("🚿 Shower Time: " + entry[3] + " minutes");
            System.out.println("🚽 Light Flushes: " + entry[4]);
            System.out.println("🚽 Heavy Flushes: " + entry[5]);
            System.out.println("🧺 Laundry: " + entry[6]);
            System.out.println("👐 Hand Washes: " + entry[7]);
            System.out.println("🍽️ Dish Washes: " + entry[8]);
            System.out.println("----------------------------------");

            System.out.print("🔄 View the next record? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                reviewUserData(data, index + 1, username, scanner); // Recursive call
            } else {
                System.out.println("🏁 Exiting water usage review.");
            }
        } else {
            reviewUserData(data, index + 1, username, scanner); // Skip unrelated entry
        }
    }
}
