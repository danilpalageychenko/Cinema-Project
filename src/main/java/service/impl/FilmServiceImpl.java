package service.impl;


import dao.impl.FilmDaoImpl;
import dao.api.Dao;
import dto.FilmDTO;
import mapper.BeanMapper;
import model.Film;
import service.api.Service;

import java.util.List;

public class FilmServiceImpl implements Service<FilmDTO> {

    private static service.impl.FilmServiceImpl service;
    private Dao<Film> filmDao;
    private BeanMapper beanMapper;

    private FilmServiceImpl() {
        filmDao = FilmDaoImpl.getInstance();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized service.impl.FilmServiceImpl getInstance() {
        if (service == null) {
            service = new service.impl.FilmServiceImpl();
        }
        return service;
    }


    @Override
    public List<FilmDTO> getAll() {
        List<Film> films = filmDao.getAll();
        List<FilmDTO> filmsDTOs = beanMapper.listMapToList(films, FilmDTO.class);
        return filmsDTOs;
    }

    @Override
    public void save(FilmDTO filmdto) {
        Film film = beanMapper.singleMapper(filmdto, Film.class);
        filmDao.save(film);
    }

    @Override
    public FilmDTO getById(long id) {
        Film film = filmDao.getById(id);
        FilmDTO filmsDTOs = beanMapper.singleMapper(film, FilmDTO.class);
        return filmsDTOs;
    }

    public FilmDTO getByName(String name) {
        Film film = filmDao.getBy("Name",name);
        FilmDTO filmsDTOs = beanMapper.singleMapper(film, FilmDTO.class);
        return filmsDTOs;
    }


    @Override
    public void delete(long key) {
        filmDao.delete(key);
    }

    @Override
    public void update(FilmDTO entity) {

    }

}
