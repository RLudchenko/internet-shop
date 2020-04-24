package mate.academy.internetshop;

import java.util.Arrays;
import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user1 = new User("John", "Marston", "123");
        User user2 = new User("Joel", "Jackson", "124");
        User user3 = new User("Matt", "Eversmann", "125");

        productService.create(new Product("iPhone X", 777.99));
        productService.create(new Product("iPhone XR", 925.99));
        productService.create(new Product("iPad", 1499.99));
        productService.create(new Product("iPad Pro", 2199.99));

        userService.create(user1);
        userService.create(user2);
        userService.create(user3);

        userService.delete(user1.getId());

        user2.setLogin("login123");
        userService.update(user2);

        user3.setLogin("Special");
        userService.update(user3);

        productService.getAll().forEach(it -> System.out.println(it.toString()));
        System.out.println("\n*********************************************\n");

        productService.delete(2L);
        productService.update(productService.get(3L));

        userService.getAll().forEach(u -> System.out.println(u.toString()));
    }
}
