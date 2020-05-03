package mate.academy.internetshop.controllers.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

@WebServlet("/cart/delete")
public class RemoveProductFromCartController extends HttpServlet {
    private static final long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(USER_ID), productService
                .get(Long.parseLong(req.getParameter("id"))));
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}