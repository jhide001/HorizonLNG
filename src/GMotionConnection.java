import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GMotionConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@//localhost:1521/XE";
        String username = "C##horizonlng";
        String password = "endur123";

        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("GMotion Database Connected Successfully!");
            System.out.println("Connection: " + conn);
            conn.close();
            System.out.println("Connection Closed.");
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
    }

}