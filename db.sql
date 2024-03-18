-- CREATE DATABASE ssafy;

use ssafy;

-- drop table if exists CropFavorites;
-- drop table if exists GardenFavorites;
-- drop table if exists RecipeFavorites;
-- drop table if exists User;
-- drop table if exists Crop;

-- drop table if exists CropRecipe;
-- drop table if exists RecipeDetail;
-- drop table if exists Recipe;

-- drop table if exists Garden;


CREATE TABLE User (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sex VARCHAR(10),
    age_range VARCHAR(10),
    address VARCHAR(255)
);

CREATE TABLE Crop (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    thumbnail_url VARCHAR(255),
    image_url VARCHAR(255),
    category VARCHAR(255),
    temperature VARCHAR(255),
    difficulty VARCHAR(10),
    sunshine VARCHAR(255),
    grow_time VARCHAR(255),
    height VARCHAR(255),
    water_period VARCHAR(255),
    humidity VARCHAR(255),
    isHydroponics BOOLEAN,
    grow_start VARCHAR(255),
    water_exit VARCHAR(255),
    crop_info mediumtext,
    water_period_info VARCHAR(255),
    sunshine_info VARCHAR(255),
    humidity_info VARCHAR(255),
    temperature_info VARCHAR(255),
    effect_info VARCHAR(255),
    link VARCHAR(255),
    grow_info mediumtext
    
);

CREATE TABLE CropRecipe (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    crop_id BIGINT NOT NULL,
	recipe_id BIGINT NOT NULL
);

CREATE TABLE CropFavorites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    is_liked BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    crop_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (crop_id) REFERENCES Crop(id)
);


CREATE TABLE Recipe (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    serialnum BIGINT,
    name VARCHAR(255),
    info VARCHAR(255),
    ingredients VARCHAR(255),
    cook VARCHAR(255),
    image_url VARCHAR(255),
    difficulty VARCHAR(255),
    likes INT
);

CREATE TABLE RecipeDetail (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    recipe_id BIGINT NOT NULL,
    recipe_order INT ,
    info mediumtext,
    image_url VARCHAR(255),
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id)
);

CREATE TABLE RecipeFavorites (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    is_liked BOOLEAN NOT NULL,
	user_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
	FOREIGN KEY (recipe_id) REFERENCES Recipe(id)
);

CREATE TABLE Garden (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    address VARCHAR(255),
    area INT,
    price INT,
    tel VARCHAR(255),
	operator VARCHAR(255),
    facilites VARCHAR(255),
    apply VARCHAR(255),
    latitude DOUBLE,
    longtitude DOUBLE,
    soil_phase_symbol VARCHAR(255)
);

CREATE TABLE GardenFavorites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    is_liked BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    garden_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (garden_id) REFERENCES Garden(id)
);


