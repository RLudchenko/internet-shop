package mate.academy.internetshop.service.interfaces;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);

    Optional<User> findByLogin(String login);
}
