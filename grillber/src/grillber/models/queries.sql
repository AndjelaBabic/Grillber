-- name: insert-user!
-- insert user in users
INSERT INTO user (first_name, last_name, email, username , password)
VALUES (:first_name, :last_name, :email, :username, :password);

-- name: get-user-by-username-and-password
-- retrives a user with the given username
SELECT * FROM user
WHERE username = :username AND password = :password;

-- name: get-user-by-id
-- retrives a user with the given id
SELECT * FROM user
WHERE id = :id;

-- name: insert-order!
-- inserting order
INSERT INTO orders (userid, grillid, delivery_time, addressid, status)
VALUES (:userid, :grillid, :delivery_time, :addressid, :status);
    
-- name: insert-address!
-- inserting address
INSERT INTO address (street_name, part_of_the_city)
VALUES (:street_name, :part_of_the_city);

-- name: last-insert-id
-- get id from last inserted address
SELECT max(id) as id
FROM address;

-- name: get-all-orders
-- retrive orders
SELECT *
FROM orders;

-- name: get-all-orders-by-user-id
-- retrive orders for specific user
SELECT orders.id as id, orders.delivery_time as delivery_time, orders.status as status, address.id as addressid, address.street_name as address, grill.name AS bbq
FROM orders INNER JOIN address on orders.addressid = address.id INNER JOIN grill ON orders.grillid = grill.id
WHERE userid = :userid;

-- name: update-order!
-- updates order
UPDATE orders
SET grillid = :grillid
WHERE id = :id;

-- name: update-address!
-- updates address
UPDATE orders
SET street_name = :street_name
WHERE id = :id;

-- name: delete-order-by-id!
-- deletes from orders by id
DELETE FROM orders
  WHERE id = :id;