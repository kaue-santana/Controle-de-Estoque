CREATE DATABASE IF NOT EXISTS estoque;

CREATE TABLE tb_categoria (
    categoria_id INT AUTO_INCREMENT PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL,
    tamanho VARCHAR(20) NULL,
    embalagem VARCHAR(20) NULL
);
DESCRIBE tb_categoria;
SELECT * FROM tb_categoria;

SELECT @@autocommit;

CREATE TABLE tb_produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL, 
    preco_unitario DECIMAL(10, 2) NOT NULL, 
    unidade VARCHAR(10) NOT NULL, 
    quantidade_estoque INT NOT NULL,
    quantidade_minima INT NOT NULL,
    quantidade_maxima INT NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES tb_categoria(categoria_id)
);

SELECT nome, preco_unitario, unidade, categoria_id FROM tb_produto;

