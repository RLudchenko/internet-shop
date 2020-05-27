package rostyslav.ludchenko.internetshop.service.interfaces;

import java.util.List;
import rostyslav.ludchenko.internetshop.model.Product;
import rostyslav.ludchenko.internetshop.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userID);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
