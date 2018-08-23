package controllers;


import dto.UserDTO;
import helpers.Hasher;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@WebServlet(name = "RegisterServlet", urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String bday = request.getParameter("bday");


        if((login != null) && (password != null) && (name != null) && (surname != null) && (bday != null)){
            UserDTO userDTO = new UserDTO(login, Hasher.makehash(password),name,surname,bday,"User");
            UserServiceImpl.getInstance().save(userDTO);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else{
            request.getSession().setAttribute("registermessage", "Some error, check fields and try again");
            response.sendRedirect(request.getContextPath() + "/pages/register.jsp");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
