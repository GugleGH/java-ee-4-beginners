package com.acme.etl.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 * Класс утилит для проекта.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class ConnectionDAOImpl implements ConnectionDAO {

    // Variables declaration
    private static final Logger log = Logger.getLogger(ConnectionDAOImpl.class);
    // End of variables declaration

    @Override
    public Connection getConnection() throws SQLException {
        final String dbDriver = "org.apache.derby.jdbc.ClientDriver";
        final String dbUrl = "jdbc:derby://localhost:1527/sample";
        final String dbPassword = "APP";
        final String dbUser = "APP";
        
        try {
            Class.forName(dbDriver).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            log.fatal(ex.getMessage());
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void closeQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void closeQuietly(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void closeQuietly(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        closeQuietly(resultSet);
        closeQuietly(statement);
        closeQuietly(connection);
    }
}
