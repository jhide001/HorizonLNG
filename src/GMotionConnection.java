import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GMotionConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "C##horizonlng";
        String password = "endur123";

        return DriverManager.getConnection(url, username, password);
    }

}