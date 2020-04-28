package mate.academy.internetshop.controllers;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConfirm = req.getParameter("pwd-confirm");
        User user = new User(name, login, password);

        if (name.equals("")) {
            req.setAttribute("message", "Name cannot be an empty field!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
        else if (password.equals("") && login.equals("")) {
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
            userService.create(user);
            shoppingCartService.create(new ShoppingCart(user));
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your passwords doesn't match");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
