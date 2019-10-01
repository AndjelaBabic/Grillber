-- name: insert-user!
-- insert user in users
INSERT INTO user (first_name, last_name, username, email, password)
VALUES (:first_name, :last_name, :username, :email, :password);

-- name: get-user-by-username
-- retrives a user with the given username
SELECT * FROM user
WHERE username = :username;

-- name: get-user-by-id
-- retrives a user with the given id
SELECT * FROM user
WHERE id = :id;

-- name: insert-order!
-- inserting order
INSERT INTO orders (userid, grillid, delivery_time, pickup_time, addressid, status)
    VALUES (:userid, :grillid, :delivery_time, :pickup_time, :addressid, :status);