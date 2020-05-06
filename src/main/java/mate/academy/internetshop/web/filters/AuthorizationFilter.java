package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/orders", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders", Set.of(Role.RoleName.USER));
        protectedUrls.put("/products", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestedUrl = req.getServletPath();

        if (protectedUrls.get(requestedUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect("/login");
            return;
        }
        User user = userService.get(userId);

        if (isAuthorized(user, protectedUrls.get(requestedUrl))) {
            chain.doFilter(req, resp);
            return;
        } else {
            LOGGER.warn("ACCESS DENIED FOR THE USER WITH AN ID: " + user.getId());
            req.getRequestDispatcher("/WEB-INF/views/access/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
