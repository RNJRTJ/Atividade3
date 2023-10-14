create database console;

use console;

CREATE TABLE contatos (
id INT AUTO_INCREMENT PRIMARY KEY,
cpf VARCHAR(255),
nome VARCHAR(255),
email VARCHAR(255),
telefone VARCHAR(255)
);

CREATE TABLE promocoes (
id INT AUTO_INCREMENT PRIMARY KEY,
preco VARCHAR(255),
viagem VARCHAR(255)
);

CREATE TABLE destinos (
id INT AUTO_INCREMENT PRIMARY KEY,
contato_id INT,
promocoe_id INT,
dataViagem DATETIME,
CONSTRAINT contato_id FOREIGN KEY (contato_id) REFERENCES contatos (id),
CONSTRAINT promocoe_id FOREIGN KEY (promocoe_id) REFERENCES promocoes (id)
);


select * from console.contatos;
select * from console.promocoes;
select * from console.destinos;






