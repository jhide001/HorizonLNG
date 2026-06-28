import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleLoader {

    public static List<EndurDeal> loadDeals() {

        List<EndurDeal> deals = new ArrayList<>();

        // Your Oracle connection details
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "C##horizonlng";
        String password = "endur123";

        // The SQL query -- same as real Endur ab_tran
        String sql = "SELECT tran_num, "
                + "       tran_status, "
                + "       deal_price, "
                + "       counterparty, "
                + "       commodity, "
                + "       volume "
                + "FROM   ab_tran "
                + "WHERE  current_flag = 1 "
                + "AND    run_type = 0";

        try (Connection conn = DriverManager.getConnection(
                url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Connected to Oracle successfully!");

            while (rs.next()) {

                int    tranNum      = rs.getInt("tran_num");
                int    statusCode   = rs.getInt("tran_status");
                double dealPrice    = rs.getDouble("deal_price");
                String counterparty = rs.getString("counterparty");
                String commodity    = rs.getString("commodity");
                double volume       = rs.getDouble("volume");

                // Convert status code to TranStatus enum
                TranStatus status;
                if (statusCode == 2) {
                    status = TranStatus.VALIDATED;
                } else if (statusCode == 3) {
                    status = TranStatus.MATURED;
                } else {
                    status = TranStatus.CANCELLED;
                }

                deals.add(new EndurDeal(
                        tranNum, status, dealPrice,
                        counterparty, commodity, volume
                ));
            }

        } catch (SQLException e) {
            System.out.println("DB ERROR: " + e.getMessage());
        }

        return deals;
    }
}