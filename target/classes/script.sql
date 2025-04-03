
CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    corfoto VARCHAR(6) NOT NULL DEFAULT '808080'
);

CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY,
    idUsuario INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    texto TEXT NOT NULL,
    dataehora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUsuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comentario (
    id SERIAL PRIMARY KEY,
    idUsuario INT NOT NULL,
    titulo VARCHAR(255),
    texto TEXT NOT NULL,
    dataehora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idpost INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (idpost) REFERENCES post(id) ON DELETE CASCADE
);

INSERT INTO usuario (email, senha, nome) VALUES 
('user1@gmail.com', 'senha', 'Usuário 1'),
('user2@gmail.com', 'senha', 'Usuário 2'),
('user3@gmail.com', 'senha', 'Usuário 3'),
('user4@gmail.com', 'senha', 'Usuário 4'),
('user5@gmail.com', 'senha', 'Usuário 5'),
('user6@gmail.com', 'senha', 'Usuário 6'),
('user7@gmail.com', 'senha', 'Usuário 7'),
('user8@gmail.com', 'senha', 'Usuário 8'),
('user9@gmail.com', 'senha', 'Usuário 9'),
('user10@gmail.com', 'senha', 'Usuário 10');

INSERT INTO post (idUsuario, titulo, texto) VALUES 
(1, 'post 1', 'texto do post 1'),
(2, 'post 2', 'texto do post 2'),
(3, 'post 3', 'texto do post 3'),
(4, 'post 4', 'texto do post 4'),
(5, 'post 5', 'texto do post 5'),
(6, 'post 6', 'texto do post 6'),
(7, 'post 7', 'texto do post 7'),
(8, 'post 8', 'texto do post 8'),
(9, 'post 9', 'texto do post 9'),
(10, 'post 10', 'texto do post 10');

INSERT INTO comentario (idUsuario, titulo, texto, idpost) VALUES 
(1, 'comentario 1 no post 2', 'texto do comentario 1', 2),
(2, 'comentario 2 no post 3', 'texto do comentario 2', 3),
(3, 'comentario 3 no post 4', 'texto do comentario 3', 4),
(4, 'comentario 4 no post 5', 'texto do comentario 4', 5),
(5, 'comentario 5 no post 6', 'texto do comentario 5', 6),
(6, 'comentario 6 no post 7', 'texto do comentario 6', 7),
(7, 'comentario 7 no post 8', 'texto do comentario 7', 8),
(8, 'comentario 8 no post 9', 'texto do comentario 8', 9),
(9, 'comentario 9 no post 10', 'texto do comentario 9', 10),
(10, 'comentario 10 no post 1', 'texto do comentario 10', 1);

SELECT * FROM usuario;
SELECT * FROM post;
SELECT * FROM comentario;
