package rostyslav.ludchenko.internetshop.controllers.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rostyslav.ludchenko.internetshop.lib.Injector;
import rostyslav.ludchenko.internetshop.model.Order;
import rostyslav.ludchenko.internetshop.service.interfaces.OrderService;

@WebServlet ("/order/detail")
public class ViewOrderDetailsController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("rostyslav.ludchenko.internetshop");
    private OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idS = req.getParameter("id");
        Long id = Long.valueOf(idS);
        Order order = orderService.get(id);

        req.setAttribute("products", order.getProducts());
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }
}
