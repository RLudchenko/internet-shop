package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
}
