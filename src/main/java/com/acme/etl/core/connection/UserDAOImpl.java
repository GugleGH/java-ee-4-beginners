package com.acme.etl.core.connection;

import com.acme.etl.core.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Работа спользователями в БД.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class UserDAOImpl implements UserDAO {

    // Variables declaration
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    
    private Connection conn;
    private Statement stmt;
    // End of variables declaration
    
    public UserDAOImpl() {
//        conn = ConnectionFactory.getInstance().getConnection().getConnection();
//        stmt = conn.createStatement();
    }
    
    /**
     * Возвращает результат запроса к БД.
     * @param query запрос
     * @return результат
     * @throws SQLException ошибка работы с БД
     */
    private ResultSet getResultSetByQuery(String query) throws SQLException {
        conn = ConnectionFactory.getInstance().getConnection().getConnection();
        stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
    
    /**
     * Возвращает PreparedStatement запроса.
     * @param query запрос
     * @returnPreparedStatement
     * @throws SQLException  ошибка работы с БД
     */
    private PreparedStatement getResultSetByPreStat(String query) throws SQLException {
        conn = ConnectionFactory.getInstance().getConnection().getConnection();
        return conn.prepareStatement(query);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM APP.USERS;";
            rs = getResultSetByQuery(sql);
            while (rs.next())
                users.add(new User(rs.getInt("ID"), rs.getString("NAME")));
            
        } finally {
            ConnectionFactory.getInstance().getConnection().closeConnection(conn, stmt, rs);
        }
        
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM APP.USERS WHERE id='" + id + "';";
            rs = getResultSetByQuery(sql);
            if (rs.first())
                user = new User(rs.getInt("ID"), rs.getString("NAME"));
            
        } finally {
            ConnectionFactory.getInstance().getConnection().closeConnection(conn, stmt, rs);
        }
        
        return user;
    }

    @Override
    public int addUser(User user) throws SQLException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO APP.USERS (id, name) VALUES  (?, ?);";
            pstmt = getResultSetByPreStat(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            
            return pstmt.executeUpdate();
        } finally {
            ConnectionFactory.getInstance().getConnection().closeConnection(conn, pstmt, rs);
        }
    }
}
