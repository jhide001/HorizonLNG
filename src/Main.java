import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("\"=== Horizon Energy LNG ===");
        System.out.println("=== End-of-Day Reval Batch ===");
        System.out.println("");

        ArrayList<EndurDeal> deals = new ArrayList<>();
        deals.add(new EndurDeal(1005, "Validated", 127500.00,
                "Shell Trading", "LNG"));
        deals.add(new EndurDeal(1012, "Matured", 95000.00,
                "TotalEnergies", "Natural Gas"));
        deals.add(new EndurDeal(1037, "Cancelled", 0.00,
                "CNOOC", "LNG"));
        deals.add(new EndurDeal(1041, "Validated", 132000.00,
                "Cheniere Energy", "LNG"));

        double currentMarketPrice = 140000.00;
        int processedCount = 0;
        int skippedCount = 0;

        for (EndurDeal deal : deals) {
            try {
                if (deal.isActive()) {
                    double mtm = deal.calculateMTM(currentMarketPrice);
                    System.out.println("[PROCESSED] " + deal
                            + " | MTM: $" + mtm);
                    processedCount++;
                } else {
                    System.out.println("[SKIPPED]   " + deal);
                    skippedCount++;
                }
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        System.out.println("");
        System.out.println("=== Batch Complete ===");
        System.out.println("Processed: " + processedCount);
        System.out.println("Skipped: " + skippedCount);
        System.out.println("Total: " + deals.size());

        HashMap<Integer, EndurDeal> dealMap = new HashMap<>();
        for (EndurDeal deal : deals) {
            dealMap.put(deal.getTranNum(), deal);
        }

        System.out.println("");
        System.out.println("=== Deal Lookup ===");
        EndurDeal lookup = dealMap.get(1041);
        if (lookup != null) {
            System.out.println("Found: " + lookup);
        } else {
            System.out.println("Deal not found");
        }
    }
}