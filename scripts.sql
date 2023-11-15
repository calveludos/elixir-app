DROP DATABASE IF EXISTS elixiroffline;
CREATE DATABASE elixiroffline;
USE elixiroffline;

-- Drop tables if they exist
DROP TABLE IF EXISTS `Speech`;
DROP TABLE IF EXISTS `Inventory`;
DROP TABLE IF EXISTS `Spell`;
DROP TABLE IF EXISTS `Character`;
DROP TABLE IF EXISTS `Folder`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Currency`;
DROP TABLE IF EXISTS `Slots`;
DROP TABLE IF EXISTS `Attribute`;

CREATE TABLE `User` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(256) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` char(60) NOT NULL,
  `code_verify` char(6) NOT NULL,
  `data_register` datetime NOT NULL,
  `is_verify` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
);

CREATE TABLE `Folder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_user`) REFERENCES `User` (`id`)
);

CREATE TABLE `Attribute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `strength` tinyint NOT NULL,
  `dexterity` tinyint NOT NULL,
  `constitution` tinyint NOT NULL,
  `intelligence` tinyint NOT NULL,
  `wisdom` tinyint NOT NULL,
  `charisma` tinyint NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Character` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_alignment` int NOT NULL,
  `id_attribute` int NOT NULL,
  `id_class` int NOT NULL,
  `id_race` int NOT NULL,
  `id_folder` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `experience` int unsigned NOT NULL,
  `height` smallint unsigned DEFAULT NULL,
  `weight` smallint unsigned DEFAULT NULL,
  `current_pv` smallint DEFAULT '0',
  `max_pv` smallint unsigned NOT NULL,
  `class_armor_bonus` tinyint DEFAULT NULL,
  `appearance` text NOT NULL,
  `background` text NOT NULL,
  `image_path` text,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_attribute`) REFERENCES `Attribute` (`id`),
  FOREIGN KEY (`id_folder`) REFERENCES `Folder` (`id`)
);

CREATE TABLE `Currency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_character` int DEFAULT NULL,
  `gold` smallint DEFAULT NULL,
  `silver` smallint DEFAULT NULL,
  `copper` smallint DEFAULT NULL,
  `electrium` smallint DEFAULT NULL,
  `platinum` smallint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_character`) REFERENCES `Character` (`id`)
);

CREATE TABLE `Slots` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_character` int DEFAULT NULL,
  `I_level` tinyint DEFAULT NULL,
  `II_level` tinyint DEFAULT NULL,
  `III_level` tinyint DEFAULT NULL,
  `IV_level` tinyint DEFAULT NULL,
  `V_level` tinyint DEFAULT NULL,
  `VI_level` tinyint DEFAULT NULL,
  `VII_level` tinyint DEFAULT NULL,
  `VIII_level` tinyint DEFAULT NULL,
  `IX_level` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_character`) REFERENCES `Character` (`id`)
);

CREATE TABLE `Speech` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_character` int DEFAULT NULL,
  `id_language` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_character`) REFERENCES `Character` (`id`)
);

CREATE TABLE `Inventory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_character` int DEFAULT NULL,
  `item_id` int NOT NULL,
  `type_item_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_character`) REFERENCES `Character` (`id`)
);

CREATE TABLE `Spell` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_character` int DEFAULT NULL,
  `spell_id` int NOT NULL,
  `type_spell_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_character`) REFERENCES `Character` (`id`)
);
