package dao.impl;





import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gaara on 20/3/2017.
 */
public final class UserDaoImpl extends CrudDAO<User> {
    private final String INSERT = "Insert into User (Login, Password,Name, SurName, Bday, Role, Tickets, Money) values (?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE User SET Login = ?, Password = ?, Name = ?, SurName = ?, Bday = ?,Role = ?,Tickets = ?, Money = ? WHERE id = ?";
    private static UserDaoImpl crudDAO;

    private UserDaoImpl(Class type) {
        super(type);
    }


    public static synchronized UserDaoImpl getInstance() {
        if (crudDAO == null) {
            crudDAO = new UserDaoImpl(User.class);
        }
        return crudDAO;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getName());
        preparedStatement.setString(4, entity.getSurName());
        preparedStatement.setString(5, entity.getBday());
        preparedStatement.setString(6, entity.getRole());
        preparedStatement.setString(7, entity.getTickets());
        preparedStatement.setInt(8, entity.getMoney());
        preparedStatement.setLong(9, entity.getId());
        return preparedStatement;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getName());
        preparedStatement.setString(4, entity.getSurName());
        preparedStatement.setString(5, entity.getBday());
        preparedStatement.setString(6, entity.getRole());
        preparedStatement.setString(7, entity.getTickets());
        preparedStatement.setInt(8, entity.getMoney());
        return preparedStatement;
    }

    @Override
    public List<User> readAll(ResultSet resultSet) throws SQLException {
        List<User> result = new LinkedList<>();
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("Login"));
            user.setPassword(resultSet.getString("Password"));
            user.setName(resultSet.getString("Name"));
            user.setSurName(resultSet.getString("SurName"));
            user.setBday(resultSet.getString("Bday"));
            user.setRole(resultSet.getString("Role"));
            user.setTickets(resultSet.getString("Tickets"));
            user.setMoney(resultSet.getInt("Money"));
            result.add(user);
        }
        return result;
    }

}