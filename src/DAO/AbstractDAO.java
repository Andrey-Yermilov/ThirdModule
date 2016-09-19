package DAO;

import DBConnection.WrapperConnector;
import DTO.Entity;

import java.sql.Statement;
import java.util.List;

/**
 * class represents basis for DAO classes
 * @param <K> type of DTO entity
 * @param <T> type of primary key
 */
public abstract class AbstractDAO<K, T extends Entity> {
    protected WrapperConnector connector;

    public abstract List<T> findAll();

    public abstract T findEntityById(K id);

    public abstract boolean create(T entity);

    public abstract T update(T entity);

    /**
     * close connection
     */
    public void close() {
        connector.closeConnection();
    }

    /**
     * cloose statement
     * @param statement statement
     */
    protected void closeStatement(Statement statement) {
        connector.closeStatement(statement);
    }
}