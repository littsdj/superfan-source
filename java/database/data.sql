BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO genres (genre_name) VALUES ('Pop');
INSERT INTO genres (genre_name) VALUES ('Rock');
INSERT INTO genres (genre_name) VALUES ('Country');
INSERT INTO genres (genre_name) VALUES ('Jazz');
INSERT INTO genres (genre_name) VALUES ('Electronic');
INSERT INTO genres (genre_name) VALUES ('Hip-Hop');
INSERT INTO genres (genre_name) VALUES ('World');
INSERT INTO genres (genre_name) VALUES ('Experimental');
INSERT INTO genres (genre_name) VALUES ('Latin');
INSERT INTO genres (genre_name) VALUES ('Metal');

INSERT INTO bands (band_name, description, genre_id, band_image_id) VALUES ('The Beatles', 'test', '1', 1);
INSERT INTO bands (band_name, description, genre_id, band_image_id) VALUES ('Metallica', 'test', '10', 2);

INSERT INTO messages
(receiver_user_id, sender_band_id, message_body, send_date, is_visible)
VALUES (1, 1, 'Hello there, fans! This is a test message.', 'YYYY-MM-DD 10:00:00')

COMMIT TRANSACTION;
