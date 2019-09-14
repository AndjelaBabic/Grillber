CREATE TABLE orders(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
userid INT NOT NULL,
grillid INT NOT NULL,
delivery_time DATETIME,
pickup_time DATETIME,
addressid INT NOT NULL,
status varchar(30),
FOREIGN KEY (userid) REFERENCES user(id),
FOREIGN KEY (grillid) REFERENCES grill(id),
FOREIGN KEY (addressid) REFERENCES address(id) ON DELETE CASCADE);
--;;
insert into orders(userid, grillid, delivery_time, pickup_time, addressid, status) values
(1 , 1, '2019-02-01 16:26:13', '2019-02-23 15:07:20', 1, 'Completed');
