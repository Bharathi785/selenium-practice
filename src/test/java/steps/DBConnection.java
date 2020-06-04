package steps;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBConnection {

    public static Connection conn = null;
    public static Statement stmt = null;

    public static void createConnection() throws IOException {
        try {
            InputStream input = new FileInputStream("./src/main/resources/_database.properties");
            Properties prop = new Properties();
            prop.load(input);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connecting to Database...");
            conn = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.pass"));
            if (conn != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Unable to connect to the database-" + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Assert.fail("Unable to connect to the database-" + e.getMessage());
        }
    }
}
