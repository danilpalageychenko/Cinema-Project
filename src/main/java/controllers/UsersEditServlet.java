package controllers;


import dto.UserDTO;
import service.impl.SeasonServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "UsersEditServlet", urlPatterns={"/admin/pages/UsersEdit"})
public class UsersEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String del = request.getParameter("del");

        if(del!= null)
        {
            UserServiceImpl.getInstance().delete(Integer.parseInt(del));
        }

        List<UserDTO> userDTOList = UserServiceImpl.getInstance().getAll();
        request.setAttribute("usersDTOList", userDTOList);
        request.getRequestDispatcher("UsersEdit.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
