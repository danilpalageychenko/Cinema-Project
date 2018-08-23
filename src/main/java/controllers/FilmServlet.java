package controllers;


import dto.FilmDTO;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FilmServlet", urlPatterns={"/film"})
public class FilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDTO filmDTO = FilmServiceImpl.getInstance().getById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("filmDTO", filmDTO);
        request.getRequestDispatcher("pages/film.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
