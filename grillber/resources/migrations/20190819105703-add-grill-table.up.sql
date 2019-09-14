CREATE TABLE grill(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20),
  description VARCHAR(200),
  price double,
  image VARCHAR(50)
);
--;;
insert  into grill(name, description, price, image) values
('Signet 90','jdskjfsd',300.99 ,'/img/incidents.png'),
('Regal S590','jdskjfsd',300.99 ,'/img/slow.png'),
('Imperial XLS','jdskjfsd',300.99 ,'/img/roadworks.png'),
('KEG 2000','jdskjfsd',300.99 ,'/img/roadclose.png');