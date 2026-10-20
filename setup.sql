CREATE DATABASE IF NOT EXISTS college;
USE college;
CREATE TABLE IF NOT EXISTS departments (
 id INT PRIMARY KEY AUTO_INCREMENT,
 department_name VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS students_info (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 course VARCHAR(100) NOT NULL,
 department_id INT NULL,
 FOREIGN KEY (department_id) REFERENCES departments(id)
);
INSERT INTO departments (id,department_name) VALUES (1,'Computer Science')
 ON DUPLICATE KEY UPDATE department_name=VALUES(department_name);
INSERT INTO students_info (name,course,department_id) VALUES
 ('Asha','BCA',1),('Ravi','MCA',1);
