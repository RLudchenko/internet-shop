package rostyslav.ludchenko.internetshop.dao.interfaces;

import rostyslav.ludchenko.internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {
    ShoppingCart getByUserId(Long userId);
}
