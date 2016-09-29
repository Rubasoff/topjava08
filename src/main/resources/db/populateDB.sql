DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories) VALUES
(date '2015-05-30' + time '10:00', 'Завтрак', 500),
(date '2015-05-30' + time '10:00', 'Обед', 1000),
(date '2015-05-30' + time '20:00', 'Ужин', 500),
(date '2015-05-31' + time '10:00', 'Завтрак', 1000),
(date '2015-05-31' + time '13:00', 'Обед', 500),
(date '2015-05-31' + time '20:00', 'Ужин', 510);