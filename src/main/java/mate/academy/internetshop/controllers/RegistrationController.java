package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConfirm = req.getParameter("pwd-confirm");
        System.out.println(login + " " + password + " " + passwordConfirm);

        if (password.equals("") && login.equals("")) {
            req.setAttribute("message", "Login and password cannot be empty fields!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        } else if (login.equals("")) {
            req.setAttribute("message", "Login cannot be an empty field!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        } else if (password.equals("")) {
            req.setAttribute("message", "Password cannot be an empty field!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }

        if (password.equals(passwordConfirm)) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your passwords doesn't match");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
