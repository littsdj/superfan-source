BEGIN TRANSACTION;

DROP TABLE IF EXISTS band_owners;
DROP TABLE IF EXISTS bands;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE bands (
    band_id SERIAL,
    band_name varchar(50) NOT NULL UNIQUE,
    description varchar(2500) NOT NULL,
    genre varchar(50) NOT NULL,
	cover_image_url varchar NOT NULL,
	subgenres varchar[],
    CONSTRAINT PK_band PRIMARY KEY (band_id)
);

CREATE TABLE band_owners (
    band_id int NOT NULL,
    owner_id int NOT NULL,
    CONSTRAINT PK_band_owners PRIMARY KEY (band_id, owner_id),
    CONSTRAINT FK_band_owners_user FOREIGN KEY (owner_id) REFERENCES users(user_id),
    CONSTRAINT FK_band_owners_bands FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

COMMIT TRANSACTION;
