package rostyslav.ludchenko.internetshop.security;

import rostyslav.ludchenko.internetshop.exceptions.AuthenticationException;
import rostyslav.ludchenko.internetshop.lib.Inject;
import rostyslav.ludchenko.internetshop.lib.Service;
import rostyslav.ludchenko.internetshop.model.User;
import rostyslav.ludchenko.internetshop.service.interfaces.UserService;
import rostyslav.ludchenko.internetshop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect username or password!"));
        if (HashUtil.hashPassword(password, userFromDB.getSalt())
                .equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect username or password!");
    }
}
