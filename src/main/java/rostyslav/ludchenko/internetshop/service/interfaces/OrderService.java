package rostyslav.ludchenko.internetshop.service.interfaces;

import java.util.List;
import java.util.Optional;
import rostyslav.ludchenko.internetshop.model.Order;
import rostyslav.ludchenko.internetshop.model.Product;
import rostyslav.ludchenko.internetshop.model.User;

public interface OrderService {
    Order create(Order order);

    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(Long id);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
