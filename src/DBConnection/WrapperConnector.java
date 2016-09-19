package DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * wrapper for statement operations
 */
public class WrapperConnector {
    private Connection connection;

    public WrapperConnector() {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            System.err.println("not obtained connection " + e);
        }
    }

    /**
     * get statement
     * @return statement
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    /**
     * get prepared statement
     * @param sql template
     * @return prepared statement
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (preparedStatement != null) {
                return preparedStatement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    /**
     * close statement
     * @param statement
     */
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("statement is null " + e);
            }
        }
    }

    /**
     * close connection
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(" wrong connection" + e);
            }
        }
    }
}