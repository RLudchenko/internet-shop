package rostyslav.ludchenko.internetshop.controllers.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rostyslav.ludchenko.internetshop.lib.Injector;
import rostyslav.ludchenko.internetshop.model.Product;
import rostyslav.ludchenko.internetshop.service.interfaces.ProductService;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("rostyslav.ludchenko.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/productsAddForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceS = req.getParameter("price");
        Double price = Double.valueOf(priceS);

        productService.create(new Product(name, price));
        req.getRequestDispatcher("/WEB-INF/views/products/productsAddForm.jsp").forward(req, resp);
    }
}
