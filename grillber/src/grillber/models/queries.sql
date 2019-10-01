-- name: insert-user!
-- insert user in users
INSERT INTO user (first_name, last_name, username, email, password)
VALUES (:first_name, :last_name, :username, :email, :password);

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
INSERT INTO orders (userid, grillid, delivery_time, pickup_time, addressid, status)
    VALUES (:userid, :grillid, :delivery_time, :pickup_time, :addressid, :status);
    
-- name: get-all-orders
-- retrive orders
SELECT *
FROM orders;

-- name: get-all-orders-by-user-id
-- retrive orders for specific user
SELECT *
FROM orders
WHERE userid = :userid;

-- name: update-order!
-- updates order
UPDATE orders
SET grillid = :grillid
WHERE id = :id;

-- name: delete-order-by-id!
-- deletes from orders by id
DELETE FROM orders
  WHERE id = :id;