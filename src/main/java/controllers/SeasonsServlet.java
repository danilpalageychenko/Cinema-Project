package controllers;


import com.google.gson.Gson;
import dto.SeasonDTO;
import service.impl.FilmServiceImpl;
import service.impl.SeasonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@WebServlet(name = "SeasonsServlet", urlPatterns={"/pages/seasons"})
public class SeasonsServlet extends HttpServlet {

    public class PrepearedSeason
    {
        String filmName;
        String hallName;
        ArrayList<LocalTime> times;
        int price;

        public PrepearedSeason(String filmName, String hallName, ArrayList<LocalTime> times, int price) {
            this.filmName = filmName;
            this.hallName = hallName;
            this.times = times;
            this.price = price;
        }

        public String getFilmName() {
            return filmName;
        }

        public void setFilmName(String filmName) {
            this.filmName = filmName;
        }

        public String getHallName() {
            return hallName;
        }

        public void setHallName(String hallName) {
            this.hallName = hallName;
        }

        public ArrayList<LocalTime> getTimes() {
            return times;
        }

        public void setTimes(ArrayList<LocalTime> times) {
            this.times = times;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





        String todaydays = request.getParameter("todayday");
        String todaymonths = request.getParameter("todaymonth");
        String todayyears = request.getParameter("todayyear");

        String minusdays = request.getParameter("minusdays");
        String plusdays = request.getParameter("plusdays");

        int todayday = 0;
        int todaymonth = 0;
        int todayyear = 0;

        LocalDate sdate;

        if(minusdays != null) {
            sdate = LocalDate.of(Integer.valueOf(todayyears),Integer.valueOf(todaymonths),Integer.valueOf(todaydays));
            sdate = sdate.minusDays(1);
            todayday = sdate.getDayOfMonth();
            todaymonth = sdate.getMonthValue();
            todayyear = sdate.getYear();

        }
        else if(plusdays != null) {
            sdate = LocalDate.of(Integer.valueOf(todayyears),Integer.valueOf(todaymonths),Integer.valueOf(todaydays));
            sdate = sdate.plusDays(1);
            todayday = sdate.getDayOfMonth();
            todaymonth = sdate.getMonthValue();
            todayyear = sdate.getYear();
        }
        else {
            sdate = LocalDate.now();
            todayday = sdate.getDayOfMonth();
            todaymonth = sdate.getMonthValue();
            todayyear = sdate.getYear();
        }

        request.getSession().setAttribute("todayday", todayday);
        request.getSession().setAttribute("todaymonth", todaymonth);
        request.getSession().setAttribute("todayyear", todayyear);


        List<SeasonDTO> seasons = SeasonServiceImpl.getInstance().getAll();

        List<SeasonDTO> todayseasons = new ArrayList<>();
        for (SeasonDTO seas : seasons) {
            LocalDateTime ldt = seas.getTime();
            LocalDate ld = LocalDate.of(ldt.getYear(),ldt.getMonth(),ldt.getDayOfMonth());
            if(ld.isEqual(sdate))
                todayseasons.add(seas);
        }

        ArrayList<PrepearedSeason> pseasons = new ArrayList<>();
        boolean isAded = false;
        for (SeasonDTO seas : todayseasons) {
            for (int i = 0; i < pseasons.size();i++) {
                if(FilmServiceImpl.getInstance().getById(seas.getFilmId()).getName().equals(pseasons.get(i).filmName))
                {
                    PrepearedSeason newses = pseasons.get(i);
                    newses.times.add(LocalTime.of(seas.getTime().getHour(),seas.getTime().getMinute()));

                    pseasons.set(i,newses);

                    isAded = true;
                }

            }
            if(!isAded)
            {
                ArrayList<LocalTime> times = new ArrayList<LocalTime>();
                times.add(LocalTime.of(seas.getTime().getHour(),seas.getTime().getMinute()));
                PrepearedSeason newses = new PrepearedSeason(FilmServiceImpl.getInstance().getById(seas.getFilmId()).getName(),
                        seas.getHallName(),times, seas.getPrice());
                pseasons.add(newses);
            }
            isAded = false;
        }


        request.getSession().setAttribute("pseasons", pseasons);

        response.sendRedirect(request.getContextPath() + "/pages/seasons.jsp");


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
