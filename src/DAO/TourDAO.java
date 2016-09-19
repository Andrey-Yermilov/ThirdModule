package DAO;

import DBConnection.WrapperConnector;
import DTO.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for tours handling
 */
public class TourDAO extends AbstractDAO<Integer, Tour> {
    public static final String SQL_SELECT_ALL_TOURS = "SELECT tour.id, tour.country,tour.price,tour_type.name AS tour_type, tour.burning FROM tour,tour_type WHERE tour.type_id=tour_type.id ORDER BY tour.id;";

    public TourDAO() {
        this.connector = new WrapperConnector();
    }

    /**
     * find all available tours
     * @return list of all available tours
     */
    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.getStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_TOURS);
            while (resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getInt("id"));
                tour.setCountry(resultSet.getString("country"));
                tour.setPrice(resultSet.getDouble("price"));
                tour.setBurning(resultSet.getString("burning"));
                tour.setType(resultSet.getString("tour_type"));
                tours.add(tour);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return tours;
    }

    @Override
    public Tour findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Tour entity) {
        return false;
    }

    @Override
    public Tour update(Tour entity) {
        return null;
    }
}
