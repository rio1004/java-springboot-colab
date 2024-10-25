--liquibase formatted sql
--changeset techgeeknext:create-tables

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    description VARCHAR(45),
    type INT,
    quantity INT,
    unit_price DECIMAL
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    address VARCHAR(255)
);