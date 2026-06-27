public class JavaBasics {
    public static void main(String[] args) {
        int dealNumber = 100234;
        String counterparty = "Shell Trading";
        double notional = 50000.75;
        boolean isActive = true;

        System.out.println("Deal Number: " + dealNumber);
        System.out.println("Counterparty: " + counterparty);
        System.out.println("Notional: " + notional);
        System.out.println("Is Active: " + isActive);

        double price = 2.5;
        double quantity = 1000;
        double totalValue = price * quantity;
        System.out.println("Total Value: " + totalValue);

        int remainder = 10 % 3;

        boolean isHighValue = totalValue > 2000;
        System.out.println("Is High Value: " + isHighValue);

        if (isHighValue) {
            System.out.println("This deal requires manager approval.");
        } else {
            System.out.println("This deal is within standard limits.");
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("Processing deal " + i + " of 5");
        }

        System.out.println("Hello, I am learning Java");
    }
}