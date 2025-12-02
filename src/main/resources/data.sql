INSERT INTO users(id, username, password) VALUES
	(1, 'ROOT', '$2a$16$bpclipbz1eL//Sk.HNnx.eHAjqA9JE/6WzAy.KCilowSh.9z/04.K'),
	(2, 'guest', '$2a$12$nCm5ETxZIw6chv.Np3bWEuVjfW9.6ewkvzg8Mf2XmKNnixhEjXkj6'),
	(3, 'admin', '$2a$12$PGqVhEegkhVpTyo7AjwsYuxejLi3mJ1xiy9KQfICYlsF/SlqHQQ4W'),
	(4, 'aletheos', '$2a$12$FT7Fs33r6VEswvXkNqssFOO8qhOaQCYSKYOx7t6.eUqTZABCHsMO6'),
	(5, 'nina', '$2a$12$OOXopEXaOF1HZiQLP4ZB5e7u2.ZRfOZriQOF1ZdRhZ8e9Y.J4ckhe'),
	(6, 'vera', '$2a$12$Cwm9gp8bsSYEllT2IuiAOO.8gIERzPKzGGuHSAaw61hoeUpLUG0x2');

INSERT INTO roles(id, name) VALUES
	(1, 'SYSTEM'),
	(2, 'ADMINISTRATOR'),
	(3, 'CURATOR'),
	(4, 'USER'),
	(5, 'VISITOR');

INSERT INTO user_roles(user_id, role_id) VALUES
	(1, 1),
	(2, 5),
	(3, 2),
	(4, 3),
	(5, 4),
	(6, 4);

INSERT INTO types(type) VALUES
	('Book'),
	('Magazine'),
	('Newspaper'),
	('CD'),
	('DVD/Blu-ray'),
	('Audio book'),
	('Video game'),
	('Board game'),
	('Braile book');

INSERT INTO articles(isbn, title, author, classification, type_id) VALUES
	(16931159494, 'A Narrow Escape', 'Veronica Mars', 'Young Adult Fiction', 1),
	(3343939597, 'The Fantastic Travels of Bambi the Hedgehog', 'Gramchi', 'Children\'s Fiction', 2),
	(9959559255, 'I Found The Way Out', 'Alex Jacobson', 'Self Help', 6),
	(3141592653589, '79323Help I\'m stuck in a universe factory846264338', 'INPUT_ERROR:NOAUTH$', 'Unclassified/Other', 1),
	(143865536, 'Monopoly Around the World', 'Hasbro Inc', 'Pro-state Propaganda', 8);

INSERT INTO loans(user_id, article_id, borrowed_on, return_by) VALUES
	(6, 9959559255, '1999-12-31', '1971-01-01'),
	(4, 3343939597, '2025-12-02', '2026-01-04');
