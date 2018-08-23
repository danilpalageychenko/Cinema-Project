package service.impl;


import dao.api.Dao;
import dao.impl.HallDaoImpl;
import dto.HallDTO;
import mapper.BeanMapper;
import model.Hall;
import service.api.Service;

import java.util.List;

public class HallServiceImpl implements Service<HallDTO> {

    private static HallServiceImpl service;
    private Dao<Hall> hallDao;
    private BeanMapper beanMapper;

    private HallServiceImpl() {
        hallDao = HallDaoImpl.getInstance();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized HallServiceImpl getInstance() {
        if (service == null) {
            service = new HallServiceImpl();
        }
        return service;
    }


    @Override
    public List<HallDTO> getAll() {
        List<Hall> halls = hallDao.getAll();
        List<HallDTO> hallDTOs = BeanMapper.listMapToList(halls, HallDTO.class);
        return hallDTOs;
    }

    @Override
    public void save(HallDTO hallDTO) {
        Hall hall = BeanMapper.singleMapper(hallDTO, Hall.class);
        hallDao.save(hall);
    }

    @Override
    public HallDTO getById(long id) {
        Hall hall = hallDao.getById(id);
        HallDTO hallDTO = BeanMapper.singleMapper(hall, HallDTO.class);
        return hallDTO;
    }


    @Override
    public void delete(long key) {
        hallDao.delete(key);
    }

    @Override
    public void update(HallDTO entity) {

    }

    public HallDTO getByHallName(String value) {
        Hall hall = hallDao.getBy("HallName", value);
        return BeanMapper.singleMapper(hall, HallDTO.class);
    }



}
