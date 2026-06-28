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

        List<EndurDeal> deals = OracleLoader.loadDeals();

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