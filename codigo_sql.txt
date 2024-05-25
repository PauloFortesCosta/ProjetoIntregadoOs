 DROP DATABASE IF EXISTS ordem_servico;

CREATE DATABASE IF NOT EXISTS ordem_servico;

USE ordem_servico;

CREATE TABLE usuario (
    id INT PRIMARY KEY auto_increment,
	login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
	tipo_usuario CHAR(7) NOT NULL
);

CREATE TABLE equipamento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(255) NOT NULL,
    cor VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL 
);

CREATE TABLE funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    id_usuario INT NOT NULL,

    CONSTRAINT fk_funcionario_id_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id)
);

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    numero CHAR(11) NOT NULL,
    cpf CHAR(11) NOT NULL,
    cep CHAR(8) NOT NULL,
    id_usuario INT NOT NULL,

    CONSTRAINT fk_cliente_id_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id)
);


CREATE TABLE servico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_equipamento INT NOT NULL,
    id_cliente INT NOT NULL,
    id_funcionario INT NOT NULL,
    dt_inclusao DATETIME NOT NULL,
    dt_conclusao DATETIME NULL,
    valor_servico DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(255) NULL,
    avaliacao VARCHAR(255) NULL,

    CONSTRAINT fk_servico_equipamento_id FOREIGN KEY(id_equipamento) REFERENCES equipamento(id),
    CONSTRAINT fk_servico_cliente_id FOREIGN KEY(id_cliente) REFERENCES cliente(id),
    CONSTRAINT fk_servico_funcionario_id FOREIGN KEY(id_funcionario) REFERENCES funcionario(id)
);


CREATE TABLE peca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_servico INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_peca_servico_id FOREIGN KEY(id_servico) REFERENCES servico(id)
);



ALTER TABLE servico ADD COLUMN cod_finaliza_servico TINYINT;
ALTER TABLE servico MODIFY COLUMN cod_finaliza_servico TINYINT NOT NULL;
ALTER TABLE usuario MODIFY COLUMN login VARCHAR(255) UNIQUE NOT NULL;


INSERT INTO usuario values (null, 'admin', '1234', 'ADMIN');

/*
select * from funcionario;
SELECT * FROM cliente;
SELECT * FROM equipamento;
SELECT * FROM servico;
SELECT * FROM peca;
SELECT * FROM funcionario WHERE nome LIKE '%jo%';

SELECT servico.*
FROM servico
INNER JOIN cliente ON cliente.id = servico.id_cliente
WHERE cliente.nome LIKE '%ana%';

SELECT * FROM servico WHERE id_cliente = 1;
*/



START TRANSACTION; -- Inicia a transação

-- INSERT INTO funcionario VALUES (null, 'Victor', 'victor@email.com', 22);

-- UPDATE funcionario SET nome = 'joao', email = 'joao@email.com', idade = 21 WHERE id = 2;

-- INSERT INTO cliente VALUES (null, 'Victor', '61998112333', '11122233344', '77500000');
-- UPDATE cliente SET nome = 'joao', numero = '61911223344', cpf = '11133322244', cep = '11122233' WHERE id = 1;
-- DELETE FROM cliente WHERE id = 2;

-- INSERT INTO equipamento VALUES (null, 'xt9000', 'vermelho', 'xiaomi');
-- UPDATE equipamento SET modelo = 'cvt80000', cor = 'azul', marca = 'Samsung' WHERE id = 1;
-- DELETE FROM equipamento WHERE id = 1;

-- INSERT INTO servico VALUES (null, 2, 3, 3, NOW(), null, 200, 'teste', null);
-- UPDATE servico SET id_equipamento = 2, id_cliente = 3, id_funcionario = 4, dt_inclusao = NOW(), dt_conclusao = NOW(), valor_servico = 259, observacao = 'teste foi', avaliacao = 'otimo' WHERE id = 1;
-- DELETE FROM servico WHERE id = 1; 


-- UPDATE servico SET cod_finaliza_servico = 0 LIMIT 1000000;
-- update servico set dt_conclusao = NOW() limit 100000000;

COMMIT; -- Confirma a transação e aplica as alterações ao banco de dados


ROLLBACK; -- Cancela a transação e desfaz as alterações feitas até o momento



