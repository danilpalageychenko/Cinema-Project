package controllers;

import dto.FilmDTO;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilmsEditServlet", urlPatterns={"/admin/pages/FilmsEdit"})
public class FilmsEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String del = request.getParameter("del");

        if(del!= null)
        {
            FilmServiceImpl.getInstance().delete(Integer.parseInt(del));
        }

        List<FilmDTO> films = FilmServiceImpl.getInstance().getAll();
        request.setAttribute("films", films);
        request.getRequestDispatcher("/admin/pages/FilmsEdit.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
