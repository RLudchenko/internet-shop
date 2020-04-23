package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Inject
    private ProductDao productDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return false;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {

    }

    @Override
    public ShoppingCart getByUserId(Long userID) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return null;
    }
}
