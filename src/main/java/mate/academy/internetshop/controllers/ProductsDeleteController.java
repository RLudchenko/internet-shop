package mate.academy.internetshop.controllers;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/delete")
public class ProductsDeleteController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private final ProductService productService = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("id");

        Long id = Long.valueOf(productId);
        productService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
