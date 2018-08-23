package controllers;

import dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaara on 4/7/17.
 */

@WebServlet(name = "UserCabinetServlet", urlPatterns={"/pages/UserCabinet"})
public class UserCabinetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        request.setAttribute("user", user);
        request.getRequestDispatcher("/pages/usercabinet.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

