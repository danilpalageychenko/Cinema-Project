package controllers;

import dto.FilmDTO;
import service.impl.FilmServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gaara on 3/21/17.
 */
@WebServlet(name = "FilmListServlet", urlPatterns={"/films"}) public class FilmListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        List<FilmDTO> filmDTOList = FilmServiceImpl.getInstance().getAll();
        request.setAttribute("filmDTOList", filmDTOList);
        request.getRequestDispatcher("pages/films.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
