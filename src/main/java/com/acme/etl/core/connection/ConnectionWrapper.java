/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Абстракный класс возвращающий соединения работы с БД.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class ConnectionWrapper {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ConnectionWrapper.class);
    
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    // End of variables declaration

    public ConnectionWrapper() {
        try {
            conn = getConnection(HRUtils.loadHRload());
        } catch (SQLException ex) {
            log.fatal("Error connection DB: " + ex.getMessage(), ex);
        }
    }

    private Connection getConnection() throws SQLException {
        if (conn != null) return conn;
        
        Properties properties = new Properties();
        
        final String dbDriver = (String) properties.get("HRData.connection.Driver");
        final String dbUrl = (String) properties.get("HRData.connection.URL");
        
        Properties connectionProps = new Properties();
        connectionProps.put("user", (String) properties.get("HRData.connection.UserID"));
        connectionProps.put("password", (String) properties.get("HRData.connection.Password"));
        // postgresql
//        connectionProps.put("prepareThreshold", "1"); // Сколько надо сделать запросов что бы начать кеширование
//        connectionProps.put("preparedStatementCacheQueries", "100"); // Максимальной значение запросов в кеше (256 )
//        connectionProps.put("preparedStatementCacheSizeMiB", "100"); // Максимальное кол-во памяти что бы избежать OutOfMemoryError (5)
        
        try {
            Class.forName(dbDriver).newInstance();
        } catch (ClassNotFoundException ex) {
            log.fatal(ex.getMessage());
        } catch (InstantiationException ex) {
            log.fatal(ex.getMessage());
        } catch (IllegalAccessException ex) {
            log.fatal(ex.getMessage());
        }
        return DriverManager.getConnection(dbUrl, connectionProps);
    }
    
    public Statement getStatement() throws SQLException {
        return stmt = (stmt == null || stmt.isClosed()) ? conn.createStatement() : stmt;
    }
    
    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return pstmt = (pstmt == null || pstmt.isClosed()) ? conn.prepareStatement(query) : pstmt;
    }
    
    public void close() {
        close(null);
    }
    
    public void close(ResultSet resultSet) {
        closeQuietly(stmt);
        closeQuietly(pstmt);
        closeQuietly(resultSet);
    }
    
    private void closeQuietly(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
    
    private void closeQuietly(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    private void closeQuietly(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
