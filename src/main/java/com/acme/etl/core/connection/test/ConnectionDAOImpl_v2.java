/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.core.connection.test;

import com.acme.etl.core.connection.ConnectionDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 * Абстракный класс возвращающий соединения работы с БД.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public abstract class ConnectionDAOImpl_v2 implements ConnectionDAO {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ConnectionDAOImpl_v2.class);
    
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    // End of variables declaration

    public ConnectionDAOImpl_v2() {
        try {
            //conn = ConnectionFactory.getInstance().getConnection().getConnection();
            conn = getConnection();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(""); // А так можно инициализороваться?
        } catch (SQLException ex) {
            log.fatal("Error connection DB: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (conn != null) return conn;
        
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
