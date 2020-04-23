package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return Storage.addCart(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long cartID) {
        return Storage.shoppingCarts.stream()
                .filter(s -> s.getId().equals(cartID))
                .findFirst();
    }

    @Override
    public ShoppingCart update(ShoppingCart updatedCart) {
        ShoppingCart oldCart = get(updatedCart.getId()).get();

        if (oldCart != null) {
            delete(oldCart.getId());
            create(updatedCart);
        }
        return updatedCart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts
                .removeIf(s -> s.getId().equals(id));
    }
}
