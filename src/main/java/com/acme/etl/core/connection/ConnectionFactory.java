/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.core.connection;

/**
 * Фабрики для работы с БД.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class ConnectionFactory {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    
    private static ConnectionDAO connectionDAO = null;
    // End of variables declaration

    private ConnectionFactory() {
    }

    /**
     * Блокировка.
     */
    private static class ConnectionHRFactoryHolder {
        private final static ConnectionFactory FACTORY = new ConnectionFactory();
    }

    /**
     * Возвращает экземпляр соединения с БД.
     * @return экземпляр
     */
    public static ConnectionFactory getInstance() {
        return ConnectionHRFactoryHolder.FACTORY;
    }

    /**
     * Возвращает методы работы с соединением.
     * @return Connection
     */
    public ConnectionDAO getConnection() {
        if (connectionDAO == null)
            connectionDAO = new ConnectionDAOImpl();

        return connectionDAO;
    }
}
