package controllers;


import dto.HallDTO;
import service.impl.HallServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HallsServlet", urlPatterns={"/pages/halls"})
public class HallsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hallcolor = request.getParameter("hallcolor");



        if(hallcolor == null || hallcolor.equals("")) {
            List<HallDTO> halls = HallServiceImpl.getInstance().getAll();
            request.getSession().setAttribute("halls", halls);
            response.sendRedirect(request.getContextPath() + "/pages/halls.jsp");
        }
        else{
            HallDTO hallDTO = HallServiceImpl.getInstance().getByHallName(hallcolor);
            request.getSession().setAttribute("selectedhall", hallDTO);
            response.sendRedirect(request.getContextPath() + "/pages/halls.jsp");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
