-- insert roles
INSERT INTO authorities ("name") VALUES('administrator');
INSERT INTO authorities ("name") VALUES('client');

-- insert user admin
INSERT INTO users ("password", username) VALUES('1234567890', 'admin@domain.io');

-- insert user roles
INSERT INTO user_authorities(user_id, authority_id)
SELECT u.id, a.id FROM users u JOIN authorities a ON a.name = 'administrator' WHERE u.username= 'admin@domain.io';