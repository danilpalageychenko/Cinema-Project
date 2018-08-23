package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.*;
import model.Ticket;
import model.enums.Place;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaara on 4/5/17.
 */
@WebServlet(name = "BuyTicketServlet", urlPatterns={"/pages/BuyTicket"})
public class BuyTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String time = request.getParameter("time");
        String date = request.getParameter("date");
        String numbers[] = date.split("/");
        int day = Integer.parseInt(numbers[0]);
        int month = Integer.parseInt(numbers[1]);
        int year = Integer.parseInt(numbers[2]);

        numbers = time.split(":");
        int hour =  Integer.parseInt(numbers[0]);
        int minutes =  Integer.parseInt(numbers[1]);


        SeasonDTO chosedSeason = null;

        LocalDateTime ldt = LocalDateTime.of(year,month,day,hour,minutes);


        for (SeasonDTO season : SeasonServiceImpl.getInstance().getAll()) {
            if (season.getTime().isEqual(ldt)) {
                chosedSeason = season;
                break;
            }

        }

        String xs = request.getParameter("x");
        String ys = request.getParameter("y");

        if(xs != null && ys !=null) {

            UserDTO user = ((UserDTO) request.getSession().getAttribute("user"));

            if (user == null) {
                request.getSession().setAttribute("url", request.getContextPath() + "/pages/seasons");
                request.getSession().setAttribute("message", "You must be loged in");
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
                return;
            }
            if(user.getMoney() < chosedSeason.getPrice())
            {
                request.getSession().setAttribute("buyticketmessage", "Not enought money");
                response.sendRedirect(request.getContextPath() + "/pages/buyticket.jsp");
                return;
            }

            int x = Integer.parseInt(xs);
            int y = Integer.parseInt(ys);

            long chosedSeasonId = chosedSeason.getId();
            long userid = user.getId();



            TicketDTO ticketDTO = new TicketDTO(chosedSeasonId, userid);
            ArrayList<ArrayList<Place>> places = new Gson().fromJson(chosedSeason.getPlaces(), new TypeToken<ArrayList<ArrayList<Place>>>() {
            }.getType());
            TicketServiceImpl.getInstance().save(ticketDTO);
            List<TicketDTO> ticketDTOList = TicketServiceImpl.getInstance().getAll();
            ticketDTO = ticketDTOList.get(ticketDTOList.size()-1);

            places.get(y).set(x, Place.Busy);
            chosedSeason.setPlaces(new Gson().toJson(places));

            user.setMoney(user.getMoney()-chosedSeason.getPrice());
            ArrayList<Integer> usertickets = new Gson().fromJson(user.getTickets(), new TypeToken<ArrayList<Integer>>() {
            }.getType());


            usertickets.add((int)ticketDTO.getId());

            user.setTickets(new Gson().toJson(usertickets));

            SeasonServiceImpl.getInstance().update(chosedSeason);
            UserServiceImpl.getInstance().update(user);


            response.sendRedirect(request.getContextPath() + "/pages/UserCabinet");
            return;

        }


        String filmName = FilmServiceImpl.getInstance().getById(chosedSeason.getFilmId()).getName();

        request.getSession().setAttribute("chosedseason", chosedSeason);
        request.getSession().setAttribute("date", date);
        request.getSession().setAttribute("time", time);
        request.getSession().setAttribute("filmName", filmName);


        response.sendRedirect(request.getContextPath() + "/pages/buyticket.jsp");




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
