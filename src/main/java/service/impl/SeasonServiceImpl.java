package service.impl;


import dao.api.Dao;
import dao.impl.SeasonDaoImpl;
import dto.SeasonDTO;
import mapper.BeanMapper;
import model.Season;
import service.api.Service;

import java.util.List;

public class SeasonServiceImpl implements Service<SeasonDTO> {

    private static SeasonServiceImpl service;
    private Dao<Season> seasonDao;
    private BeanMapper beanMapper;

    private SeasonServiceImpl() {
        seasonDao = SeasonDaoImpl.getInstance();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized SeasonServiceImpl getInstance() {
        if (service == null) {
            service = new SeasonServiceImpl();
        }
        return service;
    }


    @Override
    public List<SeasonDTO> getAll() {
        List<Season> seasons = seasonDao.getAll();
        List<SeasonDTO> seasonDTOs = BeanMapper.listMapToList(seasons, SeasonDTO.class);
        return seasonDTOs;
    }

    @Override
    public void save(SeasonDTO seasonDTO) {
        Season season = BeanMapper.singleMapper(seasonDTO, Season.class);
        seasonDao.save(season);
    }

    @Override
    public SeasonDTO getById(long id) {
        Season season = seasonDao.getById(id);
        SeasonDTO seasonDTO = BeanMapper.singleMapper(season, SeasonDTO.class);
        return seasonDTO;
    }


    @Override
    public void delete(long key) {
        seasonDao.delete(key);
    }

    @Override
    public void update(SeasonDTO entity) {
        Season season = BeanMapper.singleMapper(entity, Season.class);
        seasonDao.update(season);

    }

    public List<SeasonDTO> getByFilmId(String value) {
        List<Season> seasons = seasonDao.getListBy("filmId", value);
        return BeanMapper.listMapToList(seasons, SeasonDTO.class);
    }



}
