package com.acme.etl.core.connection;

import com.acme.etl.core.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для работы пользователями.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public interface UserDAO {

    // Variables declaration
    // End of variables declaration

    /**
     * Возвращает пользователей.
     * @return список пользователей
     * @throws java.sql.SQLException ошибка работы с БД
     */
    public List<User> getUsers() throws SQLException;
    
    /**
     * Возвращает пользователя по его идентификатору.
     * @param id идентификатор
     * @return пользователь
     * @throws java.sql.SQLException ошибка работы с БД
     */
    public User getUserById(int id) throws SQLException;
    
    /**
     * Добавление пользователя.
     * @param user пользователь
     * @return кол-во добавленных пользователей
     * @throws java.sql.SQLException ошибка работы с БД
     */
    public int addUser(User user) throws SQLException;
}
