CREATE TABLE user
(id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 first_name VARCHAR(30) NOT NULL,
 last_name VARCHAR(30) NOT NULL,
 username VARCHAR(30) NOT NULL UNIQUE,
 email VARCHAR(30) NOT NULL,
 password VARCHAR(300) NOT NULL
 );
--;;
insert into user (first_name,last_name,username,email,password) values
('Test','Test','test123','test@hotmail.com','sifra123');

