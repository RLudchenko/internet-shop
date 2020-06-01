package rostyslav.ludchenko.internetshop.controllers.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rostyslav.ludchenko.internetshop.lib.Injector;
import rostyslav.ludchenko.internetshop.model.Order;
import rostyslav.ludchenko.internetshop.service.interfaces.OrderService;
import rostyslav.ludchenko.internetshop.service.interfaces.ShoppingCartService;

@WebServlet("/admin/orders")
public class ViewAllOrdersController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("rostyslav.ludchenko.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> orders = orderService.getAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/admin/orders.jsp").forward(req, resp);
        resp.sendRedirect("/admin/orders.jsp");
    }
}
