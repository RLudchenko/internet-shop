package rostyslav.ludchenko.internetshop.dao.interfaces;

import java.util.Optional;
import rostyslav.ludchenko.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
