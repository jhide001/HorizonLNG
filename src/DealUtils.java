public class DealUtils {

    public static String formatCurrency(double amount) {
        return String.format("$%,.2f", amount);
    }

    public static String formatMTM(double mtm) {
        if (mtm >= 0) {
            return String.format("MTM: $%,.2f", mtm);
        } else {
            return String.format("MTM: -$%,.2f", Math.abs(mtm));
        }
    }
}