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


@WebServlet(name = "LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO userDTO = UserServiceImpl.getInstance().getByLogin(login);


        if(userDTO != null && userDTO.getPassword().equals(Hasher.makehash(password))){
            request.getSession().setAttribute("user", userDTO);
            if (request.getSession().getAttribute("url")!=null)
                response.sendRedirect(request.getSession().getAttribute("url").toString());
            else
                response.sendRedirect("/");
        }else{
            request.getSession().setAttribute("message", "Wrong users name or password");
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
