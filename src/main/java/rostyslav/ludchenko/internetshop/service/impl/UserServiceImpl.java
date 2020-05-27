package rostyslav.ludchenko.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import rostyslav.ludchenko.internetshop.dao.interfaces.UserDao;
import rostyslav.ludchenko.internetshop.lib.Inject;
import rostyslav.ludchenko.internetshop.lib.Service;
import rostyslav.ludchenko.internetshop.model.User;
import rostyslav.ludchenko.internetshop.service.interfaces.UserService;
import rostyslav.ludchenko.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt));
        user.setSalt(salt);
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
