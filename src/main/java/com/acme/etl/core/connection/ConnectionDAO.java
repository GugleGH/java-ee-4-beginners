package com.acme.etl.core.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Интерфейс для работы с классом уитилит.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public interface ConnectionDAO {

    // Variables declaration
    // End of variables declaration

    /**
     * Создание соединения с БД поумолчанию.
     * @return соединение с БД
     * @throws SQLException ошибка соединения
     */
    public Connection getConnection() throws SQLException;

    /**
     * Закрывает <code>connection</code> без каких либо сообщений об ошибке в случае их возникновения.
     * @param connection соединение
     */
    public void closeQuietly(Connection connection);

    /**
     * Закрывает <code>statement</code> без каких либо сообщений об ошибке в случае их возникновения.
     * @param statement statement
     */
    public void closeQuietly(Statement statement);

    /**
     * Закрывает <code>resultSet</code> без каких либо сообщений об ошибке в случае их возникновения.
     * @param resultSet resultSet
     */
    public void closeQuietly(ResultSet resultSet);

    /**
     * Закрывает <code>connection</code>, <code>statement</code>, <code>resultSet</code>
     * без каких либо сообщений об ошибке в случае их возникновения.
     *
     * Порядок закрытия: <br>
     * <code>resultSet</code> <br>
     * <code>statement</code> <br>
     * <code>connection</code> <br>
     *
     * @param connection соединение
     * @param statement statement
     * @param resultSet resultSet
     */
    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet);
}
