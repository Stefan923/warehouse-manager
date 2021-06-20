CREATE SCHEMA IF NOT EXISTS `warehousedb`;
USE `warehousedb`;

CREATE TABLE IF NOT EXISTS `Client` (`id` INT AUTO_INCREMENT, `name` VARCHAR(64), `address` VARCHAR(64), `email` VARCHAR(32), `age` INT, PRIMARY KEY(`id`));
CREATE TABLE IF NOT EXISTS `Product` (`id` INT AUTO_INCREMENT, `name` VARCHAR(64), `price` FLOAT, `stock` INT, PRIMARY KEY(`id`));
CREATE TABLE IF NOT EXISTS `Order` (`id` INT AUTO_INCREMENT, `clientId` INT, `productId` INT, `quantity` INT, PRIMARY KEY(`id`));

-- -----------------------------------------------------------------
--                           Table Inserts
-- -----------------------------------------------------------------

INSERT INTO `Client` (`name`, `address`, `email`, `age`) VALUES
	('John Smith', 'London, 33 North Road', 'john.smith@gmail.com', 32),
	('Joe Smith', 'London, 9357 School Lane', 'joe.smith@yahoo.com', 43),
	('Mary Adams', 'London, 72 Station Road', 'mary.adams@gmail.com', 38),
	('Charles Baker', 'London, 212 Queens Road', 'charles.baker@gmail.com', 27);

INSERT INTO `Product` (`name`, `price`, `stock`) VALUES
	('Apple', 1.5, 120),
	('Bread', 4, 150),
	('Bottle of milk', 15, 60),
	('Another product', 75, 10);

-- -----------------------------------------------------------------
--                              Triggers
-- -----------------------------------------------------------------

DROP TRIGGER IF EXISTS `ON_PRODUCT_DELETE`;
DELIMITER //
CREATE TRIGGER `ON_CLIENT_DELETE` BEFORE DELETE ON `Client` FOR EACH ROW
BEGIN
	DELETE FROM `Order` WHERE `clientId` = OLD.`id`;
END;
// DELIMITER ;

DROP TRIGGER IF EXISTS `ON_PRODUCT_DELETE`;
DELIMITER //
CREATE TRIGGER `ON_PRODUCT_DELETE` BEFORE DELETE ON `Product` FOR EACH ROW
BEGIN
	DELETE FROM `Order` WHERE `productId` = OLD.`id`;
END;
// DELIMITER ;