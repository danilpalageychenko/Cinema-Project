package dao.impl;



import dao.api.Dao;
import dblib.ConnectionsPool;
import model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class CrudDAO<T extends Entity> implements Dao<T> {

    private Class<T> type;
    private ConnectionsPool dataSource;

    public static final String SELECT_ALL = "Select * from %s";
    public static final String FIND_BY_ID = "Select * from %s where id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";
    public static final String FIND_BY = "Select * from %s where %s = ?";

    public CrudDAO(Class<T>  type) {
        this.type = type;
        dataSource = ConnectionsPool.getInstance();
    }

    @Override
    public List<T> getAll() {
        String sql = String.format(SELECT_ALL, type.getSimpleName());

        List result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();){
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T getById(long id) {
        String sql = String.format(FIND_BY_ID, type.getSimpleName());

        List result = null;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T) result.get(0);
    }

    @Override
    public void save(T entity) {
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = createInsertStatement(connection, entity)){
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getBy(String fieldName, String value) {
        String sql = String.format(FIND_BY, type.getSimpleName(), fieldName);
        Connection connection = dataSource.getConnection();
        List result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (result.size() > 0) ? (T) result.get(0) : null;
    }

    @Override
    public List<T> getListBy(String fieldName, String value) {
        String sql = String.format(FIND_BY, type.getSimpleName(), fieldName);
        Connection connection = dataSource.getConnection();
        List<T> result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void update(T entity) {
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = createUpdateStatement(connection, entity)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long key) {
        String sql = String.format(DELETE_BY_ID, type.getSimpleName());

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException;

    protected abstract List<T> readAll(ResultSet resultSet) throws SQLException;

}
