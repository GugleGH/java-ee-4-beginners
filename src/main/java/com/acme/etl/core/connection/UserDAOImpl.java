package com.acme.etl.core.connection;

import com.acme.etl.core.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    private final ConnectionWrapper connWrap;
    // End of variables declaration
    
    public UserDAOImpl() {
        connWrap = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt;
        try {
            String sql = "SELECT * FROM APP.USERS;";
            pstmt = connWrap.getPreparedStatement(sql);
            
            rs = pstmt.executeQuery();
            while (rs.next())
                users.add(new User(rs.getInt("ID"), rs.getString("NAME")));
            
        } finally {
            ConnectionFactory.getInstance().getConnection().close(rs);
        }
        
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        ResultSet rs = null;
        PreparedStatement pstmt;
        try {
            String sql = "SELECT * FROM APP.USERS WHERE id='?';";
            pstmt = connWrap.getPreparedStatement(sql);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            if (rs.first())
                user = new User(rs.getInt("ID"), rs.getString("NAME"));
            
        } finally {
            ConnectionFactory.getInstance().getConnection().close(rs);
        }
        
        return user;
    }

    @Override
    public int addUser(User user) throws SQLException {
        ResultSet rs = null;
        PreparedStatement pstmt;
        try {
            String sql = "INSERT INTO APP.USERS (id, name) VALUES  (?, ?);";
            pstmt = connWrap.getPreparedStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            
            rs = pstmt.executeQuery();
            
            return pstmt.executeUpdate();
        } finally {
            ConnectionFactory.getInstance().getConnection().close(rs);
        }
    }
}
