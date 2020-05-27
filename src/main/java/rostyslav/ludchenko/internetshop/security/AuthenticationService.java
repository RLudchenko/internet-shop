package rostyslav.ludchenko.internetshop.security;

import rostyslav.ludchenko.internetshop.exceptions.AuthenticationException;
import rostyslav.ludchenko.internetshop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
