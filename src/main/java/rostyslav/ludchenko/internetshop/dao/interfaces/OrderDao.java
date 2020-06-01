package rostyslav.ludchenko.internetshop.dao.interfaces;

import java.util.List;
import rostyslav.ludchenko.internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(Long userId);
}
