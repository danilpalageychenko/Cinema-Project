package controllers;

import dto.FilmDTO;
import model.enums.Genre;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet(name = "AddFilmServlet", urlPatterns={"/admin/pages/AddFilm"})
@MultipartConfig
public class AddFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String name = request.getParameter("name");
        String descr = request.getParameter("descr");
        String year = request.getParameter("year");
        String country = request.getParameter("country");
        String directors = request.getParameter("directors");
        String studio = request.getParameter("studio");
        String genre = request.getParameter("genre");
        String length = request.getParameter("length");
        String ageRestr = request.getParameter("ageRestr");

        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        System.out.println(name);
        System.out.println(descr);
        System.out.println(year);
        System.out.println(country);
        System.out.println(directors);
        System.out.println(studio);
        System.out.println(genre);
        System.out.println(length);
        System.out.println(ageRestr);




        if (name != null && descr != null && year != null && country != null && directors != null && studio != null && genre != null && length != null && ageRestr != null && inputStream != null) {


            FilmDTO filmDTO = new FilmDTO(name, descr, Integer.valueOf(year), country, directors, studio, Genre.valueOf(genre), Integer.valueOf(length), Integer.valueOf(ageRestr));
            filmDTO.setImageFromStream(inputStream);

            FilmServiceImpl.getInstance().save(filmDTO);
            response.sendRedirect(request.getContextPath() + "/admin/pages/FilmsEdit");
        } else {
            request.getSession().setAttribute("addfilmmessage", "Something wrong, check fields");
            response.sendRedirect(request.getContextPath() + "/admin/pages/AddFilm.jsp");
        }
    }


        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            doPost(request, response);
        }
    }