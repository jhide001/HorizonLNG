import java.util.ArrayList;
import java.util.List;

public class MyFirstScript {

    public static void main(String[] args) {

        System.out.println("I am learning Java for Endur");

        String counterparty = "Shell Trading";
        double dealPrice = 50000.00;
        int tranNum = 1005;

        System.out.println("Deal: " + tranNum + " | "
                + counterparty + " | " + dealPrice);

        if (dealPrice > 100000) {
            System.out.println("Alert: Deal requires manager approval");
        } else {
            System.out.println("Deal approval automatically");
        }

        System.out.println();
        System.out.println("=== Processing Deal Batch ===");

        List<EndurDeal> deals = new ArrayList<>();
        deals.add(new EndurDeal(1005, TranStatus.VALIDATED,
                127500.00, "Shell Trading", "LNG", 50000.0));
        deals.add(new EndurDeal(1012, TranStatus.MATURED,
                95000.00, "TotalEnergies", "GAS", 75000.0));
        deals.add(new EndurDeal(1037, TranStatus.CANCELLED,
                0.00, "CNOOC", "LNG", 100000.0));
        deals.add(new EndurDeal(1041, TranStatus.VALIDATED,
                132000.00, "Cheniere Energy", "LNG", 25000.0));
        deals.add(new EndurDeal(1055, TranStatus.VALIDATED,
                88000.00, "BP Energy", "GAS", 60000.0));

        for (EndurDeal deal : deals) {
            try {
                validateDeal(deal);
                logSuccess(deal);
            } catch (Exception e) {
                logError(deal, e.getMessage());
            }
        }
    }

    public static void logSuccess(EndurDeal deal) {
        System.out.println("[PROCESSED] Deal: " + deal.getTranNum()
                + " | " + deal.getCounterparty()
                + " | " + deal.getCommodity()
                + " | $" + deal.getDealPrice());
    }

    public static void logError(EndurDeal deal, String reason) {
        System.out.println("[ERROR] Deal: " + deal.getTranNum()
                + " | " + deal.getCounterparty()
                + " failed - " + reason);
    }

    public static void validateDeal(EndurDeal deal) throws Exception {
        if (deal.getTranNum() <= 0) {
            throw new Exception("Deal number must be greater than zero");
        }
        if (!deal.isActive()) {
            throw new Exception("Deal is not active - status: "
                    + deal.getTranStatus());
        }
    }

}