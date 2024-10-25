

-- product
CREATE TABLE sql12740343.product (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  description VARCHAR(45) NULL,
  type INT NULL,
  quantity INT NULL,
  unit_price DECIMAL NULL,
  PRIMARY KEY (`id`));


-- user
CREATE TABLE sql12740343.user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NULL,
  firstname VARCHAR(255) NULL,
  lastname VARCHAR(255) NULL,
  address VARCHAR(255) NULL,
  PRIMARY KEY (id));