package dao.impl;

import model.Hall;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gaara on 3/27/17.
 */

public final class HallDaoImpl extends CrudDAO<Hall> {
    private final String INSERT = "Insert into Hall (HallName,Places) values (?,?)";
    private final String UPDATE = "UPDATE Hall SET HallName = ?, Places = ? WHERE id = ?";
    private static HallDaoImpl crudDAO;

    private HallDaoImpl(Class type) {
        super(type);
    }


    public static synchronized HallDaoImpl getInstance() {
        if (crudDAO == null) {
            crudDAO = new HallDaoImpl(Hall.class);
        }
        return crudDAO;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Hall entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, entity.getHallName());
        preparedStatement.setString(2, entity.getPlaces());
        preparedStatement.setLong(3, entity.getId());
        return preparedStatement;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Hall entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getHallName());
        preparedStatement.setString(2, entity.getPlaces());
        return preparedStatement;
    }

    @Override
    public List<Hall> readAll(ResultSet resultSet) throws SQLException {
        List<Hall> result = new LinkedList<>();
        Hall hall = null;
        while (resultSet.next()) {
            hall = new Hall();
            hall.setId(resultSet.getLong("id"));
            hall.setHallName(resultSet.getString("HallName"));
            hall.setPlaces(resultSet.getString("Places"));
            result.add(hall);
        }
        return result;
    }

}
