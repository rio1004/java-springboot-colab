--liquibase formatted sql
--changeset techgeeknext:create-tables

ALTER TABLE `user` 
    ADD COLUMN  age int;