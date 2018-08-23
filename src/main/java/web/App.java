package web;


import com.google.gson.Gson;
import dto.FilmDTO;
import mapper.BeanMapper;
import model.Film;
import model.HallsInfo;
import service.impl.FilmServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<FilmDTO> filmDTOList = FilmServiceImpl.getInstance().getAll();

        for (FilmDTO f : filmDTOList) {
            System.out.println(f.getName() + "-" + f.getImage());

            BeanMapper beanMapper = BeanMapper.getInstance();
            Film film = beanMapper.singleMapper(f, Film.class);
            System.out.println(film.getImage());
        }



    }
}