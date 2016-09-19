package DAO;

import DBConnection.WrapperConnector;
import DTO.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for users handling
 */
public class UserDAO extends AbstractDAO<Integer, User> {
    public static final String SQL_SELECT_ALL_USERS = "SELECT user.id,user.name,user.login,user.password, role.role, user.uid FROM user,role WHERE user.role_id=role.id";
    public static final String SQL_SELECT_USER_BY_LOGIN = "SELECT user.id,user.name,user.login,user.password, role.role, user.uid FROM user,role WHERE user.role_id=role.id AND user.login=?";
    public static final String SQL_SELECT_USER_BY_COOKIE = "SELECT user.id,user.name,user.login,user.password, role.role, user.uid FROM user,role WHERE user.role_id=role.id AND uid=?";
    public static final String SQL_UPDATE_COOKIE = "UPDATE user SET uid=? WHERE id=?";

    public UserDAO() {
        this.connector = new WrapperConnector();
    }

    /**
     * find all users
     * @return list of all users
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.getStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setUid(resultSet.getString("uid"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return users;
    }

    @Override
    public User findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    /**
     * find user by login
     * @param login user's login
     * @return user
     */
    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setUid(resultSet.getString("uid"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return user;
    }

    /**
     * find user by cookie
     * @param uid user's cookie
     * @return user
     */
    public User findUserByCookie(String uid) {
        User user = null;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_USER_BY_COOKIE);
            st.setString(1, uid);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setUid(resultSet.getString("uid"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return user;
    }

    /**
     * update user's cookie
     * @param userId user's id
     * @param uid new cookei
     * @return number of affected rows
     */
    public int updateCookie(int userId, String uid) {
        PreparedStatement st = null;
        int affectedRows = 0;
        try {
            st = connector.prepareStatement(SQL_UPDATE_COOKIE);
            st.setString(1, uid);
            st.setInt(2, userId);
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return affectedRows;
    }
}
