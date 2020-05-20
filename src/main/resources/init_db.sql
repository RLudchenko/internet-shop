CREATE SCHEMA `internet-shop` DEFAULT CHARACTER SET utf8;
CREATE TABLE `orders`
(
    `order_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`  bigint NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `user_order_id_fk_idx` (`user_id`),
    CONSTRAINT `user_order_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders_products`
(
    `order_id`   bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `order_id_fk_idx` (`order_id`),
    KEY `product_id_fk_idx` (`product_id`),
    CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `order_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `products`
(
    `product_id` bigint       NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL,
    `price`      double       NOT NULL,
    PRIMARY KEY (`product_id`),
    UNIQUE KEY `id_UNIQUE` (`product_id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles`
(
    `role_id`   bigint       NOT NULL AUTO_INCREMENT,
    `role_name` varchar(256) NOT NULL,
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `shopping_carts`
(
    `cart_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`cart_id`),
    KEY `cart_user_id_fk_idx` (`user_id`),
    CONSTRAINT `cart_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `shopping_carts_products`
(
    `cart_id`    bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `cart_id_fk_idx` (`cart_id`),
    KEY `product_id_fk_idx` (`product_id`),
    CONSTRAINT `cart_id_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`),
    CONSTRAINT `product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users`
(
    `user_id`  bigint       NOT NULL AUTO_INCREMENT,
    `name`     varchar(255) NOT NULL,
    `login`    varchar(225) NOT NULL,
    `password` varchar(225) NOT NULL,
    `salt`     VARBINARY(500) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `login_UNIQUE` (`login`),
    UNIQUE KEY `salt_UNIQUE` (`salt`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `user_role_id_fk_idx` (`user_id`),
    KEY `role_user_id_fk_idx` (`role_id`),
    CONSTRAINT `role_user_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `user_role_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
