package controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HallDTO;
import model.HallsInfo;
import model.enums.Place;
import service.impl.HallServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddHallServlet", urlPatterns={"/admin/pages/AddHall"})
public class AddHallServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jsonString = request.getParameter("jsonString");
        String hallName = request.getParameter("hallName");

        if(jsonString != null && (hallName != null)) {
            HallsInfo hallsInfo = new Gson().fromJson(jsonString,HallsInfo.class);
            HallDTO hallDTO = new HallDTO(hallName,hallsInfo.getPlaces());
            HallServiceImpl.getInstance().save(hallDTO);
            response.sendRedirect(request.getContextPath() + "/admin/pages/HallsEdit");
        }
        else{
            request.getSession().setAttribute("message", "Something wrong, check fields");
            response.sendRedirect(request.getContextPath() + "/admin/pages/AddHall.jsp");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
