package service.impl;


import dao.api.Dao;
import dao.impl.UserDaoImpl;
import dto.UserDTO;
import mapper.BeanMapper;
import model.Season;
import model.User;
import service.api.Service;

import java.util.List;

public class UserServiceImpl implements Service<UserDTO> {

    private static UserServiceImpl service;
    private Dao<User> userDao;
    private BeanMapper beanMapper;

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized UserServiceImpl getInstance() {
        if (service == null) {
            service = new UserServiceImpl();
        }
        return service;
    }


    @Override
    public List<UserDTO> getAll() {
        List<User> users = userDao.getAll();
        List<UserDTO> filmDTOs = BeanMapper.listMapToList(users, UserDTO.class);
        return filmDTOs;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = BeanMapper.singleMapper(userDTO, User.class);
        userDao.save(user);
    }

    @Override
    public UserDTO getById(long id) {
        User user = userDao.getById(id);
        UserDTO userDTO = BeanMapper.singleMapper(user, UserDTO.class);
        return userDTO;
    }


    @Override
    public void delete(long key) {
        userDao.delete(key);
    }

    @Override
    public void update(UserDTO entity) {
        User user = BeanMapper.singleMapper(entity, User.class);
        userDao.update(user);
    }

    public UserDTO getByLogin(String value) {
        User user = userDao.getBy("login", value);
        return BeanMapper.singleMapper(user, UserDTO.class);
    }



}
