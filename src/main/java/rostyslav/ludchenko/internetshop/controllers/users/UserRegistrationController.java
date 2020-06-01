package rostyslav.ludchenko.internetshop.controllers.users;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rostyslav.ludchenko.internetshop.lib.Injector;
import rostyslav.ludchenko.internetshop.model.Role;
import rostyslav.ludchenko.internetshop.model.ShoppingCart;
import rostyslav.ludchenko.internetshop.model.User;
import rostyslav.ludchenko.internetshop.service.interfaces.ShoppingCartService;
import rostyslav.ludchenko.internetshop.service.interfaces.UserService;

@WebServlet("/registration")
public class UserRegistrationController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("rostyslav.ludchenko.internetshop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConfirm = req.getParameter("pwd-confirm");
        User user = new User(name, login, password);

        if (password.equals(passwordConfirm)) {
            user.setRoles(Set.of(Role.of("USER")));
            userService.create(user);
            ShoppingCart cart = new ShoppingCart(user.getId());
            shoppingCartService.create(cart);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your passwords doesn't match");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
    }
}
