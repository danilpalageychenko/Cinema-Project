package dao.impl;

import model.Season;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gaara on 3/27/17.
 */

public final class SeasonDaoImpl extends CrudDAO<Season> {
    private final String INSERT = "Insert into Season (filmId,hallName, places,time, price) values (?,?,?,?,?)";
    private final String UPDATE = "UPDATE Season SET filmId = ?, hallName = ?,places = ?, time = ?, price = ? WHERE id = ?";
    private static SeasonDaoImpl crudDAO;

    private SeasonDaoImpl(Class type) {
        super(type);
    }


    public static synchronized SeasonDaoImpl getInstance() {
        if (crudDAO == null) {
            crudDAO = new SeasonDaoImpl(Season.class);
        }
        return crudDAO;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Season entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setLong(1, entity.getFilmId());
        preparedStatement.setString(2, entity.getHallName());
        preparedStatement.setString(3, entity.getPlaces());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getTime()));
        preparedStatement.setInt(5, entity.getPrice());
        preparedStatement.setLong(6, entity.getId());
        return preparedStatement;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Season entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, entity.getFilmId());
        preparedStatement.setString(2, entity.getHallName());
        preparedStatement.setString(3, entity.getPlaces());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getTime()));
        preparedStatement.setInt(5, entity.getPrice());
        return preparedStatement;
    }

    @Override
    public List<Season> readAll(ResultSet resultSet) throws SQLException {
        List<Season> result = new LinkedList<>();
        Season season = null;
        while (resultSet.next()) {
            season = new Season();
            season.setId(resultSet.getLong("id"));
            season.setFilmId(resultSet.getLong("filmId"));
            season.setHallName(resultSet.getString("hallName"));
            season.setPlaces(resultSet.getString("places"));
            season.setTime(resultSet.getTimestamp("time").toLocalDateTime());
            season.setPrice(resultSet.getInt("price"));
            result.add(season);
        }
        return result;
    }

}
