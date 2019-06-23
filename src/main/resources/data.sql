INSERT INTO Roles(id, roles) VALUES (1,'admin'),(2,'user');

INSERT INTO Users (id, login, password) VALUES (1, 'admin', 'admin'),(2, 'user', 'user');

INSERT INTO users_has_roles(users_id, roles_id) VALUES (1, 1),(1, 1);

INSERT INTO Genres (id, name) VALUES (1, 'Романтика');
INSERT INTO Genres (id, name) VALUES (2, 'Фантастика');
INSERT INTO Genres (id, name) VALUES (3, 'Ужасы');
INSERT INTO Genres (id, name) VALUES (4, 'Сказки');
INSERT INTO Genres (id, name) VALUES (5, 'Публицистика');
INSERT INTO Genres (id, name) VALUES (6, 'Учебники');
INSERT INTO Genres (id, name) VALUES (7, 'Научная');
INSERT INTO Genres (id, name) VALUES (8, 'Научно-популярная');


INSERT INTO Books 
(id, name, price, description)
VALUES 
(1, '1984', '410 грн', 'Written 70 years ago, 1984 was George Orwell chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than eve'),
(2, 'A Brief History of Time', '300 грн', 'A landmark volume in science writing by one of the great minds of our time, Stephen Hawking book explores such profound questions as: How did the universe begin—and what made its start possible?'),
(3, 'Alice Adventures in Wonderland', '239 uhy', 'In 1862 Charles Lutwidge Dodgson, a shy Oxford mathematician with a stammer, created a story about a little girl tumbling down a rabbit hole.'),
(4, 'All the President Men', '400 грн', 'The full account of the Watergate scandal from the two Washington Post reporters who broke the story. This is “the work that brought down a presidency…perhaps the most influential piece of journalism in history.'),
(5, 'Breath, Eyes, Memory', '590 грн', 'At an astonishingly young age, Edwidge Danticat has become one of our most celebrated new novelists, a writer who evokes the wonder, terror, and heartache '),
(6, 'Catch-22: 50th Anniversary Edition', '110 грн', 'This fiftieth-anniversary edition commemorates Joseph Heller masterpiece with a new introduction; critical essays and reviews by Norman Mailer, Alfred Kazin,  and much more.');

INSERT INTO Authors 
(id, name) 
VALUES 
(1, 'George Orwell'),
(2, 'Stephen Hawking'),
(3, 'Lewis Carroll'),
(4, 'Bob Woodward'),
(5, 'Edwidge Danticat'),
(6, 'Joseph Heller');

INSERT INTO books_authors 
(book_id, authors_id) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

INSERT INTO books_genres 
(book_id, genres_id) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(1, 2),
(2, 1),
(3, 2),
(4, 1),
(5, 2),
(6, 3);