package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.OrderService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private OrderDao orderDao;

    //test

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
