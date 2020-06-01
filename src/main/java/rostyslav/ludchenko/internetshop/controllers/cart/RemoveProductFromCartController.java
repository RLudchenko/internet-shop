package rostyslav.ludchenko.internetshop.controllers.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rostyslav.ludchenko.internetshop.lib.Injector;
import rostyslav.ludchenko.internetshop.model.Product;
import rostyslav.ludchenko.internetshop.model.ShoppingCart;
import rostyslav.ludchenko.internetshop.service.interfaces.ProductService;
import rostyslav.ludchenko.internetshop.service.interfaces.ShoppingCartService;

@WebServlet("/cart/delete")
public class RemoveProductFromCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR =
            Injector.getInstance("rostyslav.ludchenko.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        Product product = productService.get(Long.parseLong(req.getParameter("id")));
        shoppingCartService.deleteProduct(cart, product);

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
