package DAO;

import DBConnection.WrapperConnector;
import DTO.Entity;

import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<K, T extends Entity> {
    protected WrapperConnector connector;

    public abstract List<T> findAll();

    public abstract T findEntityById(K id);

    public abstract boolean create(T entity);

    public abstract T update(T entity);

    public void close() {
        connector.closeConnection();
    }

    protected void closeStatement(Statement statement) {
        connector.closeStatement(statement);
    }
}