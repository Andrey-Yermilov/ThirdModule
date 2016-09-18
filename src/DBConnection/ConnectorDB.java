package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectorDB {
    private static volatile Connection connection = null;

    private ConnectorDB() {
    }

    public static Connection getConnection() throws SQLException {
        Connection localInstance = connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        Properties prop = new Properties();
        prop.put("user", resource.getString("user"));
        prop.put("password", resource.getString("password"));
        prop.put("autoReconnect", resource.getString("autoReconnect"));
        prop.put("characterEncoding", resource.getString("characterEncoding"));
        prop.put("useUnicode", resource.getString("useUnicode"));


        if (localInstance == null) {
            synchronized (ConnectorDB.class) {
                localInstance = connection;
                if (localInstance == null) {
                    connection = localInstance = DriverManager.getConnection(url, prop);
                }
            }
        }
        return localInstance;
    }
}
