package mate.academy.internetshop.controllers;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/order/detail")
public class ViewOrderDetailsController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idS = req.getParameter("id");
        Long id = Long.valueOf(idS);
        List<Product> products = orderService.get(id).getProducts();
        req.setAttribute("products", products);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }
}
