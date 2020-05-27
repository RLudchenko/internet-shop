package rostyslav.ludchenko.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.log4j.Logger;
import rostyslav.ludchenko.internetshop.dao.interfaces.UserDao;
import rostyslav.ludchenko.internetshop.exceptions.DataProcessingException;
import rostyslav.ludchenko.internetshop.lib.Dao;
import rostyslav.ludchenko.internetshop.model.Role;
import rostyslav.ludchenko.internetshop.model.User;
import rostyslav.ludchenko.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJdbcImpl.class);

    @Override
    public User create(User element) {
        String query = "INSERT into users (name, login, password, salt) "
                + "VALUES (?, ?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(4, element.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            element.setUserId(resultSet.getLong(1));
            LOGGER.info(element + " Successfully Created");
            setRoleForUser(element);
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create " + element, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUser(resultSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get a user with ID: " + id, e);
        }
    }

    @Override
    public User update(User element) {
        String query = "UPDATE users SET name = ?, login = ?, password = ?, salt = ? "
                + "WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(4, element.getSalt());
            statement.setLong(5, element.getId());

            statement.executeUpdate();

            deleteUsersRoles(element.getUserId());
            LOGGER.info(element + " Has Been Successfully Updated");

            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update: " + element, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try {
            deleteUsersRoles(id);
            deleteOrdersProducts(id);
            deleteUserOrders(id);
            deleteUserShoppingCart(id);
            deleteUserShoppingCartProducts(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int numOfDeletedRows = statement.executeUpdate();
            LOGGER.info("A user with an id " + id + " has been deleted!");
            return numOfDeletedRows != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update user with an ID: " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users;";
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUser(resultSet);
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get all users ", e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUser(resultSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get a user by login: " + login, e);
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        User user = new User(name, login, password);
        user.setRoles(getRolesFromUserId(id));
        user.setUserId(id);
        user.setSalt(salt);
        return user;
    }

    private void setRoleForUser(User user) throws SQLException {
        String selectRoleIdQuery = "SELECT role_id FROM roles WHERE role_name = ?";
        String insertUsersRolesQuery = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement selectRoleStatement =
                        connection.prepareStatement(selectRoleIdQuery);
                selectRoleStatement.setString(1, role.getRoleName().name());
                ResultSet resultSet = selectRoleStatement.executeQuery();
                resultSet.next();
                PreparedStatement insertRoleStatement =
                        connection.prepareStatement(insertUsersRolesQuery);
                insertRoleStatement.setLong(1, user.getId());
                insertRoleStatement.setLong(2, resultSet.getLong("role_id"));
                insertRoleStatement.executeUpdate();
            }
        }
    }

    private Set<Role> getRolesFromUserId(Long userId) throws SQLException {
        String selectRoleNameQuery = "SELECT role_name FROM users_roles "
                + "INNER JOIN roles USING (role_id) WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectRoleNameQuery);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
            return roles;
        }
    }

    private void deleteUsersRoles(Long userId) throws SQLException {
        String deleteUserQuery = "DELETE FROM users_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteUserQuery);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteOrdersProducts(Long userId) throws SQLException {
        String query = "DELETE FROM orders_products WHERE order_id IN"
                + " (SELECT order_id FROM orders WHERE user_id = ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserOrders(Long userId) throws SQLException {
        String query = "DELETE FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserShoppingCartProducts(Long userId) throws SQLException {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id IN"
                + " (SELECT cart_id FROM shopping_carts WHERE user_id = ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }

    private void deleteUserShoppingCart(Long userId) throws SQLException {
        String query = "DELETE FROM shopping_carts WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }
}
