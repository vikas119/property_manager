--DROP DATABASE mydatabase;
CREATE DATABASE IF NOT EXISTS mydatabase;
USE mydatabase;
--ALTER TABLE lease DROP FOREIGN KEY lease_ibfk_1;
--ALTER TABLE expense DROP FOREIGN KEY expense_ibfk_1;
--DROP TABLE IF EXISTS property;
--DROP TABLE IF EXISTS expense;
--DROP TABLE IF EXISTS lease;

CREATE TABLE IF NOT EXISTS property (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    property_id INTEGER,
    description VARCHAR(500) NOT NULL,
    proposed_price INTEGER,
    FOREIGN KEY (property_id) REFERENCES property(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lease (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    property_id INTEGER,
    description VARCHAR(500) NOT NULL,
    lease_start DATE,
    lease_end DATE,
    rent INTEGER,
    security_deposit INTEGER,
    FOREIGN KEY (property_id) REFERENCES property(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tenant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
)
