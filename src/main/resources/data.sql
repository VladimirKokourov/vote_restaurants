INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT(name, description, address)
VALUES ('Burger King', 'Fast food', 'Lenina st. 5'),
       ('Burger King', 'Fast food', 'Naberezhnaya st. 8'),
       ('KFC', 'Fast food', 'Lenina st. 8'),
       ('Fish and Rice', 'Sushi', 'Lenina st. 7'),
       ('Borshch', 'Homemade food', 'Lenina st. 1');

INSERT INTO DISH(name, dish_date, price_in_cents, restaurant_id)
VALUES ('Burger', '2023-05-15', '599', 1),
       ('Cheeseburger', '2023-05-15', '699', 1),
       ('Hamburger', '2023-05-15', '799', 1),
       ('Burger', '2023-05-15', '599', 2),
       ('Cheeseburger', '2023-05-15', '699', 2),
       ('Hamburger', '2023-05-15', '799', 2),
       ('Chicken', '2023-05-15', '599', 3),
       ('Chili Wings', '2023-05-15', '599', 3),
       ('Philadelphia', '2023-05-15', '800', 4),
       ('California', '2023-05-15', '750', 4),
       ('Borshch', '2023-05-15', '350', 5),
       ('Mush Potato', '2023-05-15', '450', 5),
       ('Burger', now(), '599', 1),
       ('Cheeseburger', now(), '699', 1),
       ('Hamburger', now(), '799', 1),
       ('Burger', now(), '599', 2),
       ('Cheeseburger', now(), '699', 2),
       ('Hamburger', now(), '799', 2),
       ('Chicken Legs', now(), '699', 3),
       ('Wings', now(), '499', 3),
       ('Unagi Maki', now(), '800', 4),
       ('Alaska', now(), '750', 4),
       ('Borshch', now(), '350', 5),
       ('Grechka', now(), '400', 5);

INSERT INTO VOTE(vote_date, restaurant_id, user_id)
VALUES ('2023-05-15', 1, 1),
       ('2023-05-15', 5, 3),
       (now(), 2, 1),
       (now(), 5, 3);