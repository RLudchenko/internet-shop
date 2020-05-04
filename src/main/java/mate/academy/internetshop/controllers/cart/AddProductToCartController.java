package mate.academy.internetshop.controllers.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.security.AuthenticationService;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

@WebServlet("/products/addToCart")
public class AddProductToCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("id");
        Long productID = Long.valueOf(productId);
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        Product product = productService.get(productID);
        shoppingCartService.addProduct(cart, product);

        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
