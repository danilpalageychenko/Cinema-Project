package dao.impl;

import model.Ticket;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gaara on 4/7/17.
 */

public final class TicketDaoImpl extends CrudDAO<Ticket> {
    private final String INSERT = "Insert into Ticket (SeasonId, UserId) values (?,?)";
    private final String UPDATE = "UPDATE Ticket SET SeasonId = ?, UserId = ? WHERE id = ?";
    private static TicketDaoImpl crudDAO;

    private TicketDaoImpl(Class type) {
        super(type);
    }


    public static synchronized TicketDaoImpl getInstance() {
        if (crudDAO == null) {
            crudDAO = new TicketDaoImpl(Ticket.class);
        }
        return crudDAO;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Ticket entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setLong(1, entity.getSeasonId());
        preparedStatement.setLong(2, entity.getUserId());
        preparedStatement.setLong(3, entity.getId());
        return preparedStatement;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection connection, Ticket entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, entity.getSeasonId());
        preparedStatement.setLong(2, entity.getUserId());
        return preparedStatement;
    }

    @Override
    public List<Ticket> readAll(ResultSet resultSet) throws SQLException {
        List<Ticket> result = new LinkedList<>();
        Ticket ticket = null;
        while (resultSet.next()) {
            ticket = new Ticket();
            ticket.setId(resultSet.getLong("id"));
            ticket.setSeasonId(resultSet.getLong("SeasonId"));
            ticket.setUserId(resultSet.getLong("UserId"));
            result.add(ticket);
        }
        return result;
    }

}
