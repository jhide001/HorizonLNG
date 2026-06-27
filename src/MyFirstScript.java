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

        int[] dealNumbers = {1005, 1012, 1037, 1041, 1055, -99};

        for (int i = 0; i < dealNumbers.length; i++) {
            try {
                validateDeal(dealNumbers[i]);
                logSuccess(dealNumbers[i]);
            } catch (Exception e) {
                logError(dealNumbers[i], e.getMessage());
            }
        }
    }

    public static void logSuccess(int dealNum) {
        System.out.println("[PROCESSED] Deal: " + dealNum);
    }

    public static void logError(int dealNum, String reason) {
        System.out.println("[ERROR] Deal: " + dealNum
                + " failed - " + reason);
    }

    public static void validateDeal(int dealNum) throws Exception {
        if (dealNum <= 0) {
            throw new Exception("Deal number must be greater than zero");
        }
        if (dealNum == 1037) {
            throw new Exception("Missing price data");
        }
    }
}

