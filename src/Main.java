import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Horizon Energy LNG ===");
        System.out.println("=== End-of-Day Reval Batch ===");
        System.out.println("");

        DealRepository repo = new DealRepository();
        repo.addDeal(new PhysicalDeal(1005, TranStatus.VALIDATED, 127500.00,
                "Shell Trading", "LNG", 50000.0,
                "Sabine Pass", 2500.00));
        repo.addDeal(new EndurDeal(1012, TranStatus.MATURED, 95000.00,
                "TotalEnergies", "Natural Gas", 75000.0));
        repo.addDeal(new EndurDeal(1037, TranStatus.CANCELLED, 0.00,
                "CNOOC", "LNG", 100000.0));
        repo.addDeal(new PhysicalDeal(1041, TranStatus.VALIDATED, 132000.00,
                "Cheniere Energy", "LNG", 25000.0,
                "Freeport LNG", 3000.00));

        double currentMarketPrice = 140000.00;
        int processedCount = 0;
        int skippedCount = 0;

        for (EndurDeal deal : repo.getDeals()) {
            try {
                if (deal.isActive()) {
                    double mtm = deal.calculateMTM(currentMarketPrice);
                    System.out.println("[PROCESSED] " + deal
                            + " | " + DealUtils.formatMTM(mtm));
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
        System.out.println("Portfolio MTM: "
                + DealUtils.formatMTM(repo.getTotalMTM(currentMarketPrice)));
        System.out.println("Processed: " + processedCount);
        System.out.println("Skipped: " + skippedCount);
        System.out.println("Total: " + repo.getDealCount());

        System.out.println("");
        System.out.println("=== Deal Lookup ===");
        EndurDeal lookup = repo.findByTranNum(1041);
        if (lookup != null) {
            System.out.println("Found: " + lookup);
        } else {
            System.out.println("Deal not found");
        }

        System.out.println("");
        System.out.println("=== Active Deals Only ===");
        for (EndurDeal deal : repo.getActiveDeals()) {
            System.out.println(deal);
        }

        System.out.println("");
        System.out.println("=== Shell Trading Deals ===");
        for (EndurDeal deal : repo.findByCounterparty("Shell Trading")) {
            System.out.println(deal);
        }
    }
}