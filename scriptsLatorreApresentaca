USE bywkvhsiabss77ybfqwq;

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



SELECT u.id, u.first_name, SUM(cy.copper + cy.silver + cy.gold + cy.electrium + cy.platinium) as total_moedas, SUM(cy.copper) as total_copper, SUM(cy.silver) as total_silver, SUM(cy.gold) as total_gold, SUM(cy.electrium) as total_electrium, SUM(cy.platinium) as total_platinium
FROM `User` u
LEFT JOIN `Character` c ON u.id = c.id_user
LEFT JOIN `Currency` cy ON c.id_currency = cy.id
GROUP BY u.id, u.first_name 
HAVING total_moedas > 700
ORDER BY total_moedas DESC;






