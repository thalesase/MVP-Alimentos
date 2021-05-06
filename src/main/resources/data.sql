DROP TABLE IF EXISTS receita_ingrediente;
DROP TABLE IF EXISTS ingrediente;
DROP TABLE IF EXISTS receita;


CREATE TABLE receita (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nome VARCHAR(250) NOT NULL
);

CREATE TABLE ingrediente (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  valor DOUBLE NOT NULL
);

CREATE TABLE receita_ingrediente (
  id_receita INT,
  id_ingrediente INT,
  PRIMARY KEY (id_receita, id_ingrediente),
  FOREIGN KEY (id_receita) REFERENCES receita(id),
  FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id)
);



INSERT INTO ingrediente (id, nome, valor) VALUES

(1, 'Alface', 0.40),
(2, 'Bacon', 2.00),
(3, 'Hamburguer', 3.00),
(4, 'Ovo', 0.80),
(5, 'Queijo', 1.50);


INSERT INTO receita (id, nome) VALUES
(1, 'X-Bacon'),
(2, 'X-Burger'),
(3, 'X-Egg'),
(4, 'X-Egg Bacon');

INSERT INTO receita_ingrediente (id_receita, id_ingrediente) VALUES
(1, 2),
(1, 3),
(1, 5),

(2, 3),
(2, 5),

(3, 3),
(3, 5),
(3, 4),

(4, 3),
(4, 5),
(4, 4),
(4, 2);