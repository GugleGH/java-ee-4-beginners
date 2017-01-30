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
public class ConnectionWrap {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ConnectionWrap.class);
    
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    // End of variables declaration

    public ConnectionWrap() {
        try {
            conn = getConnection();
//            pstmt = conn.prepareStatement(""); // А так можно инициализороваться?
        } catch (SQLException ex) {
            log.fatal("Error connection DB: " + ex.getMessage(), ex);
        }
    }

    private Connection getConnection() throws SQLException {
        if (conn != null) return conn;
        
        final String dbDriver = "org.apache.derby.jdbc.ClientDriver";
        final String dbUrl = "jdbc:derby://localhost:1527/sample";

        Properties connectionProps = new Properties();
        connectionProps.put("user", "APP");
        connectionProps.put("password", "APP");
        
        // postgresql
        connectionProps.put("derby.drda.minThreads", "1"); // Сколько надо сделать запросов что бы начать кеширование
//        connectionProps.put("preparedStatementCacheQueries", "100"); // Максимальной значение запросов в кеше (256 )
//        connectionProps.put("preparedStatementCacheSizeMiB", "100"); // Максимальное кол-во памяти что бы избежать OutOfMemoryError (5)
        
        // postgresql
//        connectionProps.put("derby.drda.minThreads", "1"); // Сколько надо сделать запросов что бы начать кеширование
//        connectionProps.put("preparedStatementCacheQueries", "100"); // Максимальной значение запросов в кеше (256 )
//        connectionProps.put("preparedStatementCacheSizeMiB", "100"); // Максимальное кол-во памяти что бы избежать OutOfMemoryError (5)

        try {
            Class.forName(dbDriver).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            log.fatal(ex.getMessage());
        }
        return DriverManager.getConnection(dbUrl, connectionProps);
    }
    
    public Statement getStatement() throws SQLException {
        return stmt = (stmt==null) ? conn.createStatement() : stmt;
    }
    
    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return pstmt = (pstmt==null) ? conn.prepareStatement(query) : pstmt;
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

//    public void closeConnection(Statement statement, ResultSet resultSet) {
//        closeQuietly(resultSet);
//        closeQuietly(statement);
//    }
}
