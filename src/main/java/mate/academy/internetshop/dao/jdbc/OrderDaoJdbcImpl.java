package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoJdbcImpl.class);

    @Override
    public Order create(Order element) {
        String insertOrderQuery = "INSERT INTO orders (user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertOrderQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, element.getUser());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            element.setOrderID(resultSet.getLong(1));
            insertOrdersProducts(element);
            LOGGER.info(element + " was created.");
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create " + element, e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String selectOrderQuery = "SELECT * FROM orders WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectOrderQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                return Optional.of(order);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get order with ID " + id, e);
        }
    }

    @Override
    public List<Order> getAll() {
        String selectAllOrdersQuery = "SELECT * FROM orders;";
        List<Order> allOrders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectAllOrdersQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                allOrders.add(order);
            }
            return allOrders;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all orders", e);
        }
    }

    @Override
    public Order update(Order element) {
        String updateOrderQuery = "UPDATE orders SET user_id = ? "
                + "WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateOrderQuery);
            statement.setLong(1, element.getUser());
            statement.setLong(2, element.getId());
            statement.executeUpdate();
            deleteOrderFromOrdersProducts(element.getId());
            insertOrdersProducts(element);
            LOGGER.info(element + " was updated.");
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + element, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteOrderQuery = "DELETE FROM orders WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteOrderFromOrdersProducts(id);
            PreparedStatement statement = connection.prepareStatement(deleteOrderQuery);
            statement.setLong(1, id);
            int numberOfRowsDeleted = statement.executeUpdate();
            LOGGER.info("An order with id " + id + " was deleted.");
            return numberOfRowsDeleted != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete order with ID " + id, e);
        }
    }

    private void insertOrdersProducts(Order order) throws SQLException {
        String insertOrdersProductsQuery = "INSERT INTO orders_products (order_id, product_id) "
                + "VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                PreparedStatement insertStatement =
                        connection.prepareStatement(insertOrdersProductsQuery);
                insertStatement.setLong(1, order.getId());
                insertStatement.setLong(2, product.getId());
                insertStatement.executeUpdate();
            }
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        Order order = new Order(userId, getProductsFromOrderId(id));
        order.setOrderID(id);
        return order;
    }

    private List<Product> getProductsFromOrderId(Long orderId) throws SQLException {
        String selectProductIdQuery = "SELECT * FROM orders_products op JOIN products p "
                + "ON p.product_id = op.product_id WHERE op.order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectProductIdQuery);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Product product = new Product(name, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }

    private void deleteOrderFromOrdersProducts(Long orderId) throws SQLException {
        String deleteOrderQuery = "DELETE FROM orders_products WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteOrderQuery);
            statement.setLong(1, orderId);
            statement.executeUpdate();
        }
    }
}