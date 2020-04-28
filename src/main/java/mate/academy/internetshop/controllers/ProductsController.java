package mate.academy.internetshop.controllers;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/products")
public class ProductsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("mate.academy.internetshop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();

        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
