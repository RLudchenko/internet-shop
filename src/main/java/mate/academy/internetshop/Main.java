package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        productService.create(new Product("iPhone X", 777.99));
        productService.create(new Product("iPhone XR", 925.99));
        productService.create(new Product("iPad", 1499.99));
        productService.create(new Product("iPad Pro", 2199.99));

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user1 = new User("John", "Marston", "123");

        userService.create(user1);
        userService.delete(user1.getId());

        User user2 = new User("Joel", "Jackson", "124");
        userService.create(user2);
        user2.setLogin("login123");
        userService.update(user2);

        User user3 = new User("Matt", "Eversmann", "125");
        userService.create(user3);
        user3.setLogin("Special");
        userService.update(user3);

        productService.getAll().forEach(it -> System.out.println(it.toString()));
        System.out.println("\n*********************************************\n");

        productService.delete(2L);
        productService.update(productService.get(3L));
        System.out.println(user2.getId());

        userService.getAll().forEach(u -> System.out.println(u.toString()));

        Product product1 = new Product("iPad Pro Plus", 2400.0);
        Product product2 = new Product("Mac", 3200.0);
        Product product3 = new Product("Test", 3000.0);

        productService.create(product1);
        productService.update(product1);
        productService.create(product2);
        productService.update(product2);

        ShoppingCart shoppingCart1 = new ShoppingCart(user3);
        ShoppingCart shoppingCart2 = new ShoppingCart(user2);

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        shoppingCartService.addProduct(shoppingCart1, product1);
        shoppingCartService.addProduct(shoppingCart2, product2);

        System.out.println("\n*********************************************\n");
        System.out.println(shoppingCartService.getAllProducts(shoppingCart1));

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);

        Order order2 = orderService.completeOrder(
                shoppingCartService.getAllProducts(shoppingCart2), user2);

        System.out.println("\n*********************************************\n");
        System.out.println(shoppingCartService.getByUserId(user3.getId()));
    }
}
