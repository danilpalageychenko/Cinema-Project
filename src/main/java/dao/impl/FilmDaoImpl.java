package dao.impl;




import model.Film;
import model.enums.Genre;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gaara on 20/3/2017.
 */
public final class FilmDaoImpl extends CrudDAO<Film> {
    private final String INSERT = "Insert into Film (Name, Descr, Year, Country, Directors, Studio, Genre, Length, AgeRestr, Image) values (?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Film SET Name = ?, Descr = ?, Year = ?,Country = ?, Directors = ?,Studio= ?, Genre = ?,Length = ?,AgeRestr = ?, Image=? WHERE id = ?";
    private static FilmDaoImpl crudDAO;

    private FilmDaoImpl(Class type) {
        super(type);
    }


    public static synchronized FilmDaoImpl getInstance() {
        if (crudDAO == null) {
            crudDAO = new FilmDaoImpl(Film.class);
        }
        return crudDAO;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Film entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescr());
        preparedStatement.setInt(3, entity.getYear());
        preparedStatement.setString(4, entity.getCountry());
        preparedStatement.setString(5, entity.getDirectors());
        preparedStatement.setString(6, entity.getStudio());
        preparedStatement.setString(7, entity.getGenre().toString());
        preparedStatement.setInt(8, entity.getLength());
        preparedStatement.setInt(9, entity.getAgeRestr());
        preparedStatement.setBlob(10, new SerialBlob(Base64.getDecoder().decode(entity.getImage())));
        preparedStatement.setLong(11, entity.getId());
        return preparedStatement;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Film entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescr());
        preparedStatement.setInt(3, entity.getYear());
        preparedStatement.setString(4, entity.getCountry());
        preparedStatement.setString(5, entity.getDirectors());
        preparedStatement.setString(6, entity.getStudio());
        preparedStatement.setString(7, entity.getGenre().toString());
        preparedStatement.setInt(8, entity.getLength());
        preparedStatement.setInt(9, entity.getAgeRestr());
        preparedStatement.setBlob(10, new SerialBlob(Base64.getDecoder().decode(entity.getImage())));
        return preparedStatement;
    }

    @Override
    public List<Film> readAll(ResultSet resultSet) throws SQLException {
        List<Film> result = new LinkedList<>();
        Film film = null;
        while (resultSet.next()) {
            film = new Film();
            film.setId(resultSet.getLong("id"));
            film.setName(resultSet.getString("Name"));
            film.setDescr(resultSet.getString("Descr"));
            film.setYear(resultSet.getInt("Year"));
            film.setCountry(resultSet.getString("Country"));
            film.setDirectors(resultSet.getString("Directors"));
            film.setStudio(resultSet.getString("Studio"));
            film.setGenre(Genre.valueOf(resultSet.getString("Genre")));
            film.setLength(resultSet.getInt("Length"));
            film.setAgeRestr(resultSet.getInt("AgeRestr"));
            film.setImageFromBlob(resultSet.getBlob("Image"));

            result.add(film);
        }
        return result;
    }

}