package rostyslav.ludchenko.internetshop.service.interfaces;

import java.util.Optional;
import rostyslav.ludchenko.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
