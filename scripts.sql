USE `bywkvhsiabss77ybfqwq`;

-- Drop tables if they exist
DROP TABLE IF EXISTS `Speech`;
DROP TABLE IF EXISTS `Inventory`;
DROP TABLE IF EXISTS `Character`;
DROP TABLE IF EXISTS `Folder`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Currency`;
DROP TABLE IF EXISTS `Slots`;
DROP TABLE IF EXISTS `Attribute`;

-- Create tables
CREATE TABLE `Currency` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `gold` SMALLINT,
    `silver` SMALLINT,
    `copper` SMALLINT,
    `electrium` SMALLINT,
    `platinium` SMALLINT
);

CREATE TABLE `User` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(100) NOT NULL,
    `first_name` VARCHAR(40) NOT NULL,
    `last_name` VARCHAR(40) NOT NULL,
    `password` CHAR(60) NOT NULL,
    `code_verify` CHAR(8) NOT NULL,
    `data_register` DATETIME  NOT NULL,
    `is_verify` BOOL DEFAULT 0
);

CREATE TABLE `Attribute` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `strength` TINYINT NOT NULL,
    `dexterity` TINYINT NOT NULL,
    `constitution` TINYINT NOT NULL,
    `intelligence` TINYINT NOT NULL,
    `wisdom` TINYINT NOT NULL,
    `charisma` TINYINT NOT NULL
);

CREATE TABLE `Slots` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `I_level` TINYINT,
    `II_level` TINYINT,
    `III_level` TINYINT,
    `IV_level` TINYINT,
    `V_level` TINYINT,
    `VI_level` TINYINT,
    `VII_level` TINYINT,
    `VIII_level` TINYINT,
    `IX_level` TINYINT
);

CREATE TABLE `Folder` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_user` INT,
    `name` VARCHAR(30) NOT NULL,
    `color` CHAR(7) DEFAULT "#FFFFFF",
    FOREIGN KEY (`id_user`) REFERENCES `User`(`id`)
);

CREATE TABLE `Character` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_alignment` INT NOT NULL,
    `id_attribute` INT NOT NULL,
    `id_class` INT NOT NULL,
    `id_race` INT NOT NULL,
    `id_folder` INT,
    `id_user` INT NOT NULL,
    `id_currency` INT NOT NULL,
    `id_slots` INT,
    `name` VARCHAR(50) NOT NULL,
    `player_name` VARCHAR(50) NOT NULL,
    `experience` INT UNSIGNED NOT NULL,
    `level` SMALLINT UNSIGNED NOT NULL,
    `height` SMALLINT UNSIGNED,
    `weight` SMALLINT UNSIGNED,
    `current_pv` SMALLINT DEFAULT 0,
    `max_pv` SMALLINT UNSIGNED NOT NULL,
    `class_armor_bonus` TINYINT,
    `apperance` TEXT NOT NULL,
    `background` TEXT NOT NULL,
    `image_path` TEXT,
    FOREIGN KEY (`id_attribute`) REFERENCES `Attribute`(`id`),
    FOREIGN KEY (`id_folder`) REFERENCES `Folder`(`id`),
    FOREIGN KEY (`id_user`) REFERENCES `User`(`id`),
    FOREIGN KEY (`id_currency`) REFERENCES `Currency`(`id`),
    FOREIGN KEY (`id_slots`) REFERENCES `Slots`(`id`)
);

CREATE TABLE `Inventory` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `character_id` INT,
    `item_id` INT NOT NULL,
    `type_item_id` INT NOT NULL,
    FOREIGN KEY (`character_id`) REFERENCES `Character`(`id`)
);

CREATE TABLE `Speech` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_character` INT,
    `id_language` INT NOT NULL,
    FOREIGN KEY (`id_character`) REFERENCES `Character`(`id`)
);

-- Inserir dados na tabela Currency
INSERT INTO Currency (gold, silver, copper, electrium, platinium)
VALUES (500, 250, 100, 0, 2),
	   (750, 375, 150, 1, 3),
	   (2000, 1000, 400, 3, 10),
	   (300, 150, 60, 0, 1),
	   (1, 0, 2, 0, 0),
	   (250, 125, 50, 0, 1),
	   (800, 400, 160, 1, 4),
	   (1200, 600, 240, 2, 6),
	   (6000, 3000, 1200, 6, 30),
	   (900, 450, 180, 1, 9),
	   (1500, 750, 300, 2, 5),
	   (400, 200, 80, 0, 2),
	   (2200, 1100, 440, 4, 11),
	   (180, 90, 36, 0, 2),
	   (5, 2, 3, 0, 0);

-- Inserir dados na tabela User
INSERT INTO User (email, first_name, last_name, password, code_verify, data_register, is_verify)
VALUES ('maria@hotmail.com', 'Maria', 'Santos', '$2a$12$48YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.BWG', '98765432', '2023-09-04 11:30:00', 1),
       ('pedro@gmail.com', 'Pedro', 'Ferreira', '$2a$12$36YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.CRG', '54321678', '2023-09-04 12:15:00', 1),
       ('ana@yahoo.com', 'Ana', 'Oliveira', '$2a$12$24YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.DVF', '65432198', '2023-09-04 13:45:00', 1),
       ('carlos@gmail.com', 'Carlos', 'Pereira', '$2a$12$15YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.ECF', '78901234', '2023-09-04 14:20:00', 1),
       ('lucia@hotmail.com', 'Lucia', 'Ribeiro', '$2a$12$27YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.FTG', '12345678', '2023-09-04 15:00:00', 1),
       ('andre@gmail.com', 'Andre', 'Almeida', '$2a$12$18YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.GVH', '23456789', '2023-09-04 16:30:00', 1),
       ('marcos@gmail.com', 'Marcos', 'Sousa', '$2a$12$23YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.HJK', '45678901', '2023-09-04 17:45:00', 1),
       ('anaclara@hotmail.com', 'Ana Clara', 'Lima', '$2a$12$28YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.LMN', '56789012', '2023-09-04 18:10:00', 1),
       ('felipe@gmail.com', 'Felipe', 'Rodrigues', '$2a$12$37YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.OPQ', '67890123', '2023-09-04 19:20:00', 1),
       ('luisa@gmail.com', 'Luisa', 'Costa', '$2a$12$45YXyYRLshKaZBzoLwzjPO69i5C4NWyk6m6fvNu2FwjJ2I90E.RST', '78901234', '2023-09-04 20:30:00', 1);

-- Inserir dados na tabela Attribute
INSERT INTO Attribute (strength, dexterity, constitution, intelligence, wisdom, charisma)
VALUES (12, 14, 10, 18, 16, 12),
       (8, 10, 14, 12, 10, 16),
       (14, 12, 16, 14, 10, 12),
       (10, 16, 12, 12, 14, 14),
       (16, 14, 12, 10, 12, 18),
       (12, 10, 14, 18, 14, 12),
       (14, 16, 12, 12, 10, 10),
       (10, 12, 16, 14, 12, 14),
       (12, 12, 14, 16, 18, 10),
       (14, 14, 14, 14, 14, 14),
       (12, 10, 14, 18, 14, 12),
       (14, 16, 12, 12, 10, 10),
       (10, 12, 16, 14, 12, 14),
       (12, 12, 14, 16, 18, 10),
       (14, 14, 14, 14, 14, 14);

-- Inserir dados na tabela Slots
INSERT INTO Slots (I_level, II_level, III_level, IV_level, V_level, VI_level, VII_level, VIII_level, IX_level)
VALUES (1, 0, 0, 0, 0, 0, 0, 0, 0), 
       (2, 2, 0, 0, 0, 0, 0, 0, 0), 
       (3, 2, 2, 0, 0, 0, 0, 0, 0),
       (6, 6, 5, 4, 4, 3, 3, 3, 2), 
       (3, 3, 3, 2, 2, 0, 0, 0, 0), 
       (3, 2, 1, 0, 0, 0, 0, 0, 0), 
       (8, 7, 6, 5, 4, 4, 2, 0, 0), 
       (7, 6, 5, 5, 4, 3, 2, 0, 0), 
       (5, 5, 4, 3, 3, 2, 0, 0, 0), 
       (4, 4, 3, 2, 1, 0, 0, 0, 0); 
       
-- Inserir dados na tabela Folder
INSERT INTO Folder (id_user, name, color)
VALUES (1, 'Pasta PRG Latorre', '#00FF00'),
       (2, 'Pasta PRG Ramanovisk', '#0000FF'),
       (3, 'Pasta PRG Calamidade', '#FFFF00'),
       (4, 'Pasta PRG OSNI', '#FF00FF'),
       (5, 'Pasta PRG Aventuras', '#00FFFF'),
       (6, 'Pasta PRG Campanhas', '#FF0000'),
       (7, 'Pasta PRG Personagens', '#00FF00'),
       (8, 'Pasta PRG Itens', '#0000FF'),
       (9, 'Pasta PRG Monstros', '#FFFF00'),
       (10, 'Pasta PRG Missões', '#FF00FF');

-- Inserir dados na tabela Character
INSERT INTO `Character` (id_alignment, id_attribute, id_class, id_race, id_folder, id_user, id_currency, id_slots, player_name, name, experience, level, height, weight, current_pv, max_pv, apperance, background, image_path)
VALUES 
  (1, 1, 3, 1, 1, 1, 1, 1, 'Jogador 1', 'Gandalf', 2500, 2, 175, 70, 7, 9, 'Um mago poderoso', 'Um mago lendário', 'caminho/para/imagem1.jpg'),
  (3, 2, 3, 1, 1, 1, 2, 2, 'Jogador 1', 'Aragorn', 10000, 4, 185, 85, 8, 10, 'Um grande guerreiro', 'Um líder destemido', 'caminho/para/imagem2.jpg'),
  (2, 5, 3, 4, 3, 3, 5, 4, 'Jogador 3', 'Gollum', 1260000, 20, 130, 35, 4, 51, 'Um ex-hobbit', 'Obcecado pelo Um Anel', 'caminho/para/imagem4.jpg'),
  (1, 7, 3, 3, 5, 5, 7, 5, 'Jogador 5', 'Sauron', 460000, 10, 220, 120, 15, 25, 'O Senhor do Escuro', 'Dominador de Mordor', 'caminho/para/imagem5.jpg'),
  (3, 9, 1, 4, 10, 10, 9, 6, 'Jogador 10', 'Sam', 12000, 5, 165, 75, 7, 34, 'Um leal companheiro', 'Herói da Comitiva do Anel', '/imagens/sam.jpg'),
  (1, 12, 1, 4, 9, 9, 12, 7, 'Jogador 9', 'Smeagol', 1000000, 17, 125, 30, 4, 72, 'Um antigo hobbit', 'Obcecado pelo Um Anel', '/imagens/smeagol.jpg');

INSERT INTO `Character` (id_alignment, id_attribute, id_class, id_race, id_folder, id_user, id_currency, player_name, name, experience, level, height, weight, current_pv, max_pv, apperance, background, image_path)
VALUES 
  (3, 4, 4, 4, 2, 2, 4, 'Jogador 2', 'Frodo', 80000, 8, 160, 50, 1, 34, 'Um hobbit corajoso', 'Um portador do Um Anel', 'caminho/para/imagem6.jpg'),
  (3, 6, 4, 4, 4, 4, 6, 'Jogador 4', 'Bilbo', 400000, 11, 170, 70, 65, 32, 'Um aventureiro', 'Descobridor do Um Anel', 'caminho/para/imagem7.jpg'),
  (3, 8, 2, 2, 6, 6, 8, 'Jogador 6', 'Gimli', 408000, 11, 160, 90, 20, 81, 'Um anão valente', 'Um mestre nas batalhas', 'caminho/para/imagem8.jpg'),
  (1, 11, 2, 2, 8, 8, 11, 'Jogador 8', 'Bombur', 16000, 5, 165, 80, 21, 28, 'Um anão guerreiro', 'Mestre das machadinhas', 'caminho/para/imagem10.jpg');

INSERT INTO `Character` (id_alignment, id_attribute, id_class, id_race, id_user, id_currency, player_name, name, experience, level, height, weight, current_pv, max_pv, apperance, background, image_path)
VALUES 
  (1, 10, 2, 1, 7, 10, 'Jogador 7', 'Faramir', 808000, 15, 190, 80, 41, 55, 'Um líder de Gondor', 'Guardião de Gondor', 'caminho/para/imagem9.jpg');
  
INSERT INTO `Character` (id_alignment, id_attribute, id_class, id_race, id_user, id_currency, id_slots, player_name, name, experience, level, height, weight, current_pv, max_pv, apperance, background, image_path)
VALUES 
  (1, 14, 1, 1, 10, 14, 9, 'Jogador 10', 'Boromir', 500000, 12, 190, 85, 8, 59, 'Um clerigo de Gondor', 'Defensor de Gondor', '/imagens/boromir.jpg'),
  (1, 3, 3, 3, 2, 3, 3, 'Jogador 2', 'Legolas', 40000, 6, 170, 65, 6, 18, 'Um elfo habilidoso', 'Um arqueiro exímio', 'caminho/para/imagem3.jpg'),
  (3, 13, 1, 3, 10, 13, 8, 'Jogador 3', 'Galadriel', 900000, 16, 175, 50, 12, 84, 'Uma elfa sábia', 'Guardiã de Lothlórien', '/imagens/galadriel.jpg'),
  (3, 15, 1, 3, 10, 15, 10, 'Jogador 5', 'Thranduil', 200000, 9, 190, 70, 10, 43, 'Um rei clerigo', 'Governante da Floresta das Trevas', '/imagens/thranduil.jpg');

-- Inserir dados na tabela Inventory
INSERT INTO Inventory (character_id, item_id, type_item_id)
VALUES 
(1, 1, 1), (2, 2, 2), (3, 3, 3),
(4, 4, 1), (5, 5, 2), (6, 6, 3),
(7, 7, 1), (8, 8, 2), (9, 9, 3),
(10, 10, 1), (11, 11, 2), (12, 12, 3),
(13, 13, 1), (14, 14, 2), (15, 15, 3),
(1, 16, 1), (2, 17, 2), (3, 18, 3),
(4, 19, 1), (5, 20, 2), (6, 21, 3),
(7, 22, 1), (8, 23, 2), (9, 24, 3),
(10, 25, 1), (11, 26, 2), (12, 27, 3),
(13, 28, 1), (14, 29, 2), (15, 30, 3),
(1, 31, 1), (2, 32, 2), (3, 33, 3),
(4, 34, 1), (5, 35, 2), (6, 36, 3),
(7, 37, 1), (8, 38, 2), (9, 39, 3),
(10, 40, 1), (11, 41, 2), (12, 42, 3),
(13, 43, 1), (14, 44, 2), (15, 45, 3),
(1, 46, 1), (2, 47, 2), (3, 48, 3),
(4, 49, 1), (5, 50, 2), (6, 51, 3),
(7, 52, 1), (8, 53, 2), (9, 54, 3),
(10, 55, 1), (11, 56, 2), (12, 57, 3),
(13, 58, 1), (14, 59, 2), (15, 60, 3),
(1, 61, 1), (2, 62, 2), (3, 63, 3),
(4, 64, 1), (5, 65, 2), (6, 66, 3),
(7, 67, 1), (8, 68, 2), (9, 69, 3),
(10, 70, 1), (11, 71, 2), (12, 72, 3),
(13, 73, 1), (14, 74, 2), (15, 75, 3),
(1, 76, 1), (2, 77, 2), (3, 78, 3),
(4, 79, 1), (5, 80, 2), (6, 81, 3),
(7, 82, 1), (8, 83, 2), (9, 84, 3),
(10, 85, 1), (11, 86, 2), (12, 87, 3),
(13, 88, 1), (14, 89, 2), (15, 90, 3),
(1, 91, 1), (2, 92, 2), (3, 93, 3),
(4, 94, 1), (5, 95, 2), (6, 96, 3),
(7, 97, 1), (8, 98, 2), (9, 99, 3),
(10, 100, 1), (11, 101, 2), (12, 102, 3),
(13, 103, 1), (14, 104, 2), (15, 105, 3),
(1, 106, 1), (2, 107, 2), (3, 108, 3),
(4, 109, 1), (5, 110, 2), (6, 111, 3),
(7, 112, 1), (8, 113, 2), (9, 114, 3),
(10, 115, 1), (11, 116, 2), (12, 117, 3),
(13, 118, 1), (14, 119, 2), (15, 120, 3);

-- Inserir dados na tabela Speech
INSERT INTO Speech (id_character, id_language)
VALUES 
  (1, 3), (1, 6), (1, 2), (1, 8), (2, 4),
  (2, 1), (2, 7), (2, 5), (3, 2), (3, 1),
  (3, 3), (3, 8), (4, 4), (4, 6), (4, 7),
  (4, 2), (5, 5), (5, 1), (5, 3), (5, 8),
  (6, 2), (6, 4), (6, 7), (6, 6), (7, 1),
  (8, 5), (9, 1), (10, 2), (11, 8), (12, 3),
  (13, 5), (14, 4), (15, 7);

-- Faz um join entre usuario, pasta, personagem e mostra o nome de todos, escolhendo o personagem pelo id  (JOIN)
SELECT u.first_name, f.name, c.name
FROM `User` u
INNER JOIN `Folder` f ON u.id = f.id_user
INNER JOIN `Character` c ON f.id_user = c.id_folder
WHERE u.id = 1;
-- Faz um join entre personagem e seus atributos, pegando o personagem pelo id (JOIN)
SELECT c.name, a.strength, a.dexterity, a.constitution, a.intelligence, a.wisdom, a.charisma
FROM Attribute a 
INNER JOIN `Character` c ON a.id = c.id_attribute
WHERE c.id = 1;
-- Mostra o nome do personagem e o dinheiro que o mesmo tem (JOIN)
SELECT c.name, cy.copper, cy.silver, cy.gold, cy.electrium, cy.platinium
FROM Currency cy
INNER JOIN `Character` c ON c.id_currency = cy.id
WHERE c.id = 1; 



-- Faz um join entre usuario, pasta, personagem, level e ordenando do menor level para o maior (JOIN E ORDER BY)
SELECT u.first_name, f.name, c.name, c.level
FROM `User` u
INNER JOIN `Folder` f ON u.id = f.id_user
INNER JOIN `Character` c ON f.id_user = c.id_folder
ORDER BY c.level ASC;
-- Faz um join entre usuario, pasta, personagem, vida total e ordenando da menor vida para a maior (JOIN E ORDER BY)
SELECT u.first_name, f.name, c.name, c.id_race , c.max_pv
FROM `User` u
INNER JOIN `Folder` f ON u.id = f.id_user
INNER JOIN `Character` c ON f.id_user = c.id_folder
ORDER BY c.max_pv ASC;
 
 
 
-- Mostra o ID das 4 classes e a média do valor de cada atributo que elas tem (JOIN, ORDER BY E GROUP BY)
SELECT c.id_class, ROUND(AVG(a.strength)) as media_forca, ROUND(AVG(a.intelligence)) as media_inteligencia, ROUND(AVG(a.wisdom)) as media_sabedoria, ROUND(AVG(a.dexterity)) as media_destreza, ROUND(AVG(a.charisma)) as media_carisma, ROUND(AVG(a.constitution)) as media_consituicao
FROM `Character` c
JOIN Attribute a ON c.id_attribute = a.id
GROUP BY c.id_class
ORDER BY c.id_class ASC;






-- VIEWS
CREATE OR REPLACE VIEW characterMaisDinheiro AS
SELECT u.id, u.first_name, SUM(cy.copper + cy.silver + cy.gold + cy.electrium + cy.platinium) as total_moedas, SUM(cy.copper) as total_copper, SUM(cy.silver) as total_silver, SUM(cy.gold) as total_gold, SUM(cy.electrium) as total_electrium, SUM(cy.platinium) as total_platinium
FROM `User` u
LEFT JOIN `Character` c ON u.id = c.id_user
LEFT JOIN `Currency` cy ON c.id_currency = cy.id
GROUP BY u.id, u.first_name 
HAVING total_moedas > 700
ORDER BY total_moedas DESC;

CREATE OR REPLACE VIEW usuario AS
SELECT email, first_name, last_name, code_verify, data_register, is_verify
FROM `User`;


SELECT * FROM `characterMaisDinheiro`;
SELECT * FROM `usuario`;
