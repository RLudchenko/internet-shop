CREATE SCHEMA `internet-shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet-shop`.`products` (
`id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(225) NOT NULL,
`price` DOUBLE NOT NULL,
PRIMARY KEY (`id`)
);
CREATE TABLE `users` (
`userId` bigint NOT NULL AUTO_INCREMENT,
`name` varchar(225) NOT NULL,
`login` varchar(225) NOT NULL,
`password` varchar(225) NOT NULL,
PRIMARY KEY (`userId`),
UNIQUE KEY `login_UNIQUE` (`login`)
);
