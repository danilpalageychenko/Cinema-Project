package service.impl;

import dao.api.Dao;
import dao.impl.TicketDaoImpl;
import dto.TicketDTO;
import mapper.BeanMapper;
import model.Ticket;
import service.api.Service;

import java.util.List;

/**
 * Created by gaara on 4/7/17.
 */

public class TicketServiceImpl implements Service<TicketDTO> {

    private static TicketServiceImpl service;
    private Dao<Ticket> ticketDao;
    private BeanMapper beanMapper;

    private TicketServiceImpl() {
        ticketDao = TicketDaoImpl.getInstance();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized TicketServiceImpl getInstance() {
        if (service == null) {
            service = new TicketServiceImpl();
        }
        return service;
    }


    @Override
    public List<TicketDTO> getAll() {
        List<Ticket> tickets = ticketDao.getAll();
        List<TicketDTO> ticketDTOs = BeanMapper.listMapToList(tickets, TicketDTO.class);
        return ticketDTOs;
    }

    @Override
    public void save(TicketDTO ticketDTO) {
        Ticket ticket = BeanMapper.singleMapper(ticketDTO, Ticket.class);
        ticketDao.save(ticket);
    }

    @Override
    public TicketDTO getById(long id) {
        Ticket ticket = ticketDao.getById(id);
        TicketDTO ticketDTO = BeanMapper.singleMapper(ticket, TicketDTO.class);
        return ticketDTO;
    }


    @Override
    public void delete(long key) {
        ticketDao.delete(key);
    }

    @Override
    public void update(TicketDTO entity) {

    }




}
