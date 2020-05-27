package rostyslav.ludchenko.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import rostyslav.ludchenko.internetshop.dao.interfaces.OrderDao;
import rostyslav.ludchenko.internetshop.lib.Inject;
import rostyslav.ludchenko.internetshop.lib.Service;
import rostyslav.ludchenko.internetshop.model.Order;
import rostyslav.ludchenko.internetshop.model.Product;
import rostyslav.ludchenko.internetshop.model.User;
import rostyslav.ludchenko.internetshop.service.interfaces.OrderService;
import rostyslav.ludchenko.internetshop.service.interfaces.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order completeOrder(List<Product> products, Long userId) {
        return create(new Order(userId, products));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAll().stream()
                .filter(order -> order.getUser().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
