INSERT INTO users(id, username, password) VALUES
	(1, 'ROOT', '$2a$16$bpclipbz1eL//Sk.HNnx.eHAjqA9JE/6WzAy.KCilowSh.9z/04.K'),
	(2, 'aletheos', '$2a$12$FT7Fs33r6VEswvXkNqssFOO8qhOaQCYSKYOx7t6.eUqTZABCHsMO6'),
	(3, 'admin', '$2a$12$PGqVhEegkhVpTyo7AjwsYuxejLi3mJ1xiy9KQfICYlsF/SlqHQQ4W');

INSERT INTO roles(id, name) VALUES
	(1, 'ADMINISTRATOR'),
	(2, 'MODERATOR'),
	(3, 'USER');

INSERT INTO user_roles(user_id, role_id) VALUES
	(1, 1),
	(2, 3),
	(3, 2);
