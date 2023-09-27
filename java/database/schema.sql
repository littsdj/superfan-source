BEGIN TRANSACTION;

DROP TABLE IF EXISTS band_owners;
DROP TABLE IF EXISTS bands;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS subgenres;
DROP TABLE IF EXISTS band_subgenres;
DROP TABLE IF EXISTS user_following;
DROP TABLE IF EXISTS images;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE genres (
	genre_id SERIAL,
	genre_name varchar NOT NULL UNIQUE,
	CONSTRAINT PK_genres PRIMARY KEY (genre_id)
);

CREATE TABLE images (
    image_id SERIAL,
    filename varchar(100) NOT NULL,
    image_data bytea NOT NULL,
    CONSTRAINT PK_images PRIMARY KEY (image_id)
);

CREATE TABLE bands (
    band_id SERIAL,
    band_name varchar(50) NOT NULL UNIQUE,
    description varchar(2500) NOT NULL,
    genre_id int NOT NULL,
	band_image_id int,
    CONSTRAINT PK_band PRIMARY KEY (band_id),
    CONSTRAINT FK_bands_genres FOREIGN KEY (genre_id) REFERENCES genres(genre_id),
    CONSTRAINT FK_bands_images FOREIGN KEY (band_image_id) REFERENCES images(image_id)
);

CREATE TABLE subgenres (
	subgenre_id SERIAL,
	subgenre_name varchar NOT NULL UNIQUE,
	CONSTRAINT PK_subgenres PRIMARY KEY (subgenre_id)
);

CREATE TABLE band_owners (
    band_id int NOT NULL,
    owner_id int NOT NULL,
    CONSTRAINT PK_band_owners PRIMARY KEY (band_id, owner_id),
    CONSTRAINT FK_band_owners_user FOREIGN KEY (owner_id) REFERENCES users(user_id),
    CONSTRAINT FK_band_owners_bands FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE user_following (
	user_id int,
	band_id int,
	CONSTRAINT PK_user_following PRIMARY KEY (user_id),
	CONSTRAINT FK_user_following_users FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_user_following_bands FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE band_images (
    image_id int UNIQUE NOT NULL,
    band_id int NOT NULL,
    CONSTRAINT PK_band_images PRIMARY KEY (image_id),
    CONSTRAINT FK_band_images_images FOREIGN KEY (image_id) REFERENCES images(image_id),
    CONSTRAINT FK_band_images_bands FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE band_subgenres (
	band_id int,
	subgenre_id int,
	CONSTRAINT PK_band_subgenres PRIMARY KEY (band_id),
	CONSTRAINT FK_band_subgenres_bands FOREIGN KEY (band_id) REFERENCES bands(band_id),
	CONSTRAINT FK_band_subgenres_subgenres FOREIGN KEY (subgenre_id) REFERENCES subgenres(subgenre_id)
);

COMMIT TRANSACTION;
