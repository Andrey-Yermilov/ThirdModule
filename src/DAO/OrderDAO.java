package DAO;

import DBConnection.WrapperConnector;
import DTO.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO for orders handling
 */
public class OrderDAO extends AbstractDAO<Integer, Order> {
    public static final String SQL_SELECT_ALL_TOURS_BY_USERID = "SELECT cart.id, user.name, user.login, tour.country,tour.price*(1-(cart.discount)/100) as price,tour_type.name AS type, tour.burning " +
            "FROM cart, user, tour,tour_type " +
            "WHERE cart.client_id=user.id AND cart.tour_id=tour.id AND tour.type_id=tour_type.id AND user.id=?";
    public static final String SQL_CREATE_ORDER = "INSERT INTO cart (client_id, tour_id) VALUES (?,?);";

    public OrderDAO() {
        this.connector = new WrapperConnector();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    /**
     * find all orders by user's id
     * @param id user's id
     * @return list of all user's orders
     */
    public List<Order> findAllByUserId(int id) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_ALL_TOURS_BY_USERID);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserName(resultSet.getString("name"));
                order.setUserLogin(resultSet.getString("login"));
                order.setCountry(resultSet.getString("country"));
                order.setPriceWithDiscount(resultSet.getDouble("price"));
                order.setTourType(resultSet.getString("type"));
                order.setIsBurning(resultSet.getString("burning"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return orders;
    }

    /**
     * create order
     * @param tourId tour's id
     * @param userId user's id
     * @return number of affected rows
     */
    public int create(int tourId, int userId) {
        PreparedStatement st = null;
        int affectedRows = 0;
        try {
            st = connector.prepareStatement(SQL_CREATE_ORDER);
            st.setInt(1, userId);
            st.setInt(2, tourId);
            affectedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return affectedRows;
    }
}
