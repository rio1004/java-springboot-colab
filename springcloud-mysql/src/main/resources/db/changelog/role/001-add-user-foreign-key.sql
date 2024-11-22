--liquibase formatted sql
--changeset techgeeknext:create-tables

CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(45),
    type INT
);

ALTER TABLE user
ADD COLUMN role_id INT,
ADD FOREIGN KEY (role_id) REFERENCES role(id);