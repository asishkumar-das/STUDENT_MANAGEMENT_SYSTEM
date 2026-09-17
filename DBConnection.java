import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        String url = "jdbc:oracle:thin:@//Asishkumar:1521/orclRoo";
        String username = "SCOTT";
        String password = "tiger";

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            return DriverManager.getConnection(
                url,
                username,
                password
            );

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}