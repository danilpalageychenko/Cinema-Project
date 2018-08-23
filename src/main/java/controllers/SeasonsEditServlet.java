package controllers;


import dto.HallDTO;
import dto.SeasonDTO;
import model.Hall;
import model.Season;
import model.enums.Place;
import service.impl.FilmServiceImpl;
import service.impl.HallServiceImpl;
import service.impl.SeasonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SeasonsEditServlet", urlPatterns={"/admin/pages/SeasonsEdit"})
public class SeasonsEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String del = request.getParameter("del");

        if(del!= null)
        {
            SeasonServiceImpl.getInstance().delete(Integer.parseInt(del));
        }

        List<SeasonDTO> seasonDTOs = SeasonServiceImpl.getInstance().getAll();
        List<String> filmNames = new ArrayList<>();
        for (SeasonDTO season : seasonDTOs)
        {
            filmNames.add(FilmServiceImpl.getInstance().getById(season.getFilmId()).getName());
        }
        List<HallDTO> halls = HallServiceImpl.getInstance().getAll();

        request.getSession().setAttribute("seasons", seasonDTOs);
        request.getSession().setAttribute("halls", halls);
        request.getSession().setAttribute("filmNames", filmNames);

        String filmname = request.getParameter("filmname");
        String hall = request.getParameter("hall");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String price = request.getParameter("price");

        if(filmname != null && hall != null && date != null && time != null && price != null) {
            try {
                long filmid = FilmServiceImpl.getInstance().getByName(filmname).getId();
                ArrayList<ArrayList<Place>> places = HallServiceImpl.getInstance().getByHallName(hall).getListOfPlaces();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
                LocalDateTime ldt = LocalDateTime.parse(date + " " + time, formatter);

                SeasonDTO seasonDTO = new SeasonDTO(filmid, hall, places, ldt, Integer.parseInt(price));

                SeasonServiceImpl.getInstance().save(seasonDTO);
                response.sendRedirect(request.getContextPath() + "/admin/pages/SeasonsEdit");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                request.getSession().setAttribute("seasonseditmessage", "Something wrong, check fields");
                response.sendRedirect(request.getContextPath() + "/admin/pages/SeasonsEdit.jsp");
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/admin/pages/SeasonsEdit.jsp");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
