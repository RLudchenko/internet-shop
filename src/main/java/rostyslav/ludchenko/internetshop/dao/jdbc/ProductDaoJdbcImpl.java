package rostyslav.ludchenko.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import rostyslav.ludchenko.internetshop.dao.interfaces.ProductDao;
import rostyslav.ludchenko.internetshop.exceptions.DataProcessingException;
import rostyslav.ludchenko.internetshop.lib.Dao;
import rostyslav.ludchenko.internetshop.model.Product;
import rostyslav.ludchenko.internetshop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJdbcImpl.class);

    @Override
    public Product create(Product element) {
        String query = "INSERT into products (name, price) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setDouble(2, element.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            element.setId(resultSet.getLong(1));
            LOGGER.info(element + " Successfully Created");
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create " + element, e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = getProduct(resultSet);
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get a product with ID: " + id, e);
        }
    }

    @Override
    public Product update(Product element) {
        String query = "UPDATE products SET name = ?, price = ? "
                + "WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, element.getName());
            statement.setDouble(2, element.getPrice());
            statement.setLong(3, element.getId());

            statement.executeUpdate();
            LOGGER.info(element + " Has Been Successfully Updated");

            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update: " + element, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM products WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setLong(1, id);
            int numOfDeletedRows = statment.executeUpdate();
            LOGGER.info("A product with an id " + id + " has been deleted!");
            return numOfDeletedRows != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete product with ID: " + id, e);
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products;";
        List<Product> allProducts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                allProducts.add(product);
            }
            return allProducts;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get all products", e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");

        Product product = new Product(name, price);
        product.setId(id);
        return product;
    }
}
