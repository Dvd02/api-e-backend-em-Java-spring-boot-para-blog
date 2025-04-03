
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

INSERT INTO usuario (email, senha, nome, corfoto) VALUES 
('astro.viajante@gmail.com', 'galaxia123', 'Astro Viajante', '#FFD700'),
('eco.amante@gmail.com', 'verdevida', 'Eco Amante', '#228B22'),
('gamer.xtreme@gmail.com', 'playhard', 'Gamer Xtreme', '#1E90FF'),
('chef.gourmet@gmail.com', 'receitas100', 'Chef Gourmet', '#FF6347'),
('artista.dos.sons@gmail.com', 'melodia22', 'Artista dos Sons', '#8A2BE2'),
('fotografa.mundo@gmail.com', 'clickclick', 'Fotógrafa do Mundo', '#FF4500'),
('explorador.urbano@gmail.com', 'city123', 'Explorador Urbano', '#708090'),
('amante.dos.livros@gmail.com', 'livrosvida', 'Amante dos Livros', '#8B4513'),
('viajante.aventura@gmail.com', 'mundoaberto', 'Viajante de Aventuras', '#4682B4'),
('cinéfilo.cultural@gmail.com', 'filmeslife', 'Cinéfilo Cultural', '#2E8B57');

INSERT INTO post (idUsuario, titulo, texto) VALUES 
(1, 'A Conexão com o Universo', 'Descubra como meditar sob as estrelas pode mudar sua perspectiva.'),
(1, 'Viajando pelos Astros', 'Relato da minha experiência no observatório mais incrível do mundo.'),
(2, 'Plantas e Bem-Estar', 'Como o cultivo de plantas pode transformar seu ambiente e sua mente.'),
(2, 'Salvando o Planeta', 'Pequenas ações que fazem uma grande diferença no meio ambiente.'),
(3, 'Top 10 Jogos de 2024', 'Minha lista dos jogos mais incríveis que você precisa jogar agora.'),
(3, 'A Evolução dos Jogos', 'Reflexões sobre o impacto dos games na cultura atual.'),
(4, 'Receita de Lasanha Perfeita', 'Um guia passo a passo para preparar uma lasanha deliciosa.'),
(4, 'Cozinhando com Amor', 'Como a comida caseira conecta famílias e cria memórias.'),
(5, 'Música para a Alma', 'Minha jornada como compositor e as canções que me marcaram.'),
(5, 'O Poder do Silêncio', 'Explorando a importância das pausas musicais na vida e na arte.'),
(6, 'Fotografando o Pôr do Sol', 'Dicas para capturar o momento perfeito em suas fotos.'),
(6, 'Retratos que Contam Histórias', 'A arte de capturar a essência das pessoas em fotos.'),
(7, 'Os Segredos da Cidade', 'Explorando os becos mais interessantes da minha cidade.'),
(7, 'Arte Urbana: Um Retrato Social', 'Como os grafites representam vozes esquecidas.'),
(8, 'Livros que Transformam', 'Minha lista de livros que mudaram minha maneira de ver o mundo.'),
(8, 'A Magia da Leitura', 'Por que mergulhar em um bom livro é a melhor terapia.'),
(9, 'Top 5 Lugares Exóticos', 'Minha experiência visitando lugares únicos e inesquecíveis.'),
(9, 'Planeje Sua Próxima Aventura', 'Dicas para organizar uma viagem inesquecível.'),
(10, 'O Cinema como Arte', 'Por que o cinema é mais que entretenimento: é reflexão.'),
(10, 'Filmes que Inspiram', 'Minha lista de filmes que despertam criatividade e motivação.');

INSERT INTO comentario (idUsuario, titulo, texto, idpost) VALUES 
(2, 'Incrível Experiência', 'Meditar sob as estrelas é algo que quero experimentar.', 1),
(3, 'A Energia das Estrelas', 'É impressionante como o cosmos pode nos fazer sentir tão pequenos.', 1),
(4, 'Observatório Inspirador', 'Adorei saber mais sobre sua experiência no observatório!', 2),
(5, 'Astrofísica em Foco', 'O céu noturno é um verdadeiro espetáculo, obrigado por compartilhar.', 2),
(6, 'Plantas que Curam', 'Ter plantas em casa realmente melhora o ambiente.', 3),
(7, 'A Conexão com a Natureza', 'Cuidar das plantas é um momento de paz para mim.', 3),
(8, 'Pequenas Ações', 'Acredito que todos podemos contribuir para um mundo melhor.', 4),
(9, 'Sustentabilidade Importa', 'Muito inspirador! Já comecei a implementar algumas ideias.', 4),
(10, 'Jogos para Viver', 'Sua lista é incrível, vou adicionar esses jogos à minha biblioteca.', 5),
(1, 'Uma Jornada de Diversão', 'Esses jogos realmente marcaram o ano!', 5),
(2, 'Games e Cultura', 'Os jogos têm um impacto cultural enorme. Excelente reflexão!', 6),
(3, 'Memórias dos Games', 'Lembro-me de como tudo começou, e é incrível ver até onde chegamos.', 6),
(4, 'Receita Deliciosa', 'Testei a receita e ficou maravilhosa. Obrigado por compartilhar!', 7),
(5, 'Dica para a Família', 'Essa lasanha é perfeita para um almoço de domingo.', 7),
(6, 'Memórias na Cozinha', 'Nada como comida feita com amor para criar memórias.', 8),
(7, 'Receitas de Coração', 'Gostei muito da forma como conecta comida e sentimentos.', 8),
(8, 'Harmonia Interior', 'A música é realmente uma ponte para nossa essência.', 9),
(9, 'Conexão Musical', 'Obrigado por compartilhar suas reflexões sobre a música.', 9),
(10, 'Reflexão Silenciosa', 'O silêncio tem muito a nos ensinar, especialmente na música.', 10),
(1, 'Pausas Valiosas', 'Gostei do que disse sobre as pausas, isso também vale na vida.', 10),
(2, 'Beleza Natural', 'Fotografar o pôr do sol é um momento mágico. Ótimas dicas!', 11),
(3, 'Luz e Emoção', 'A luz dourada é realmente incrível para fotos.', 11),
(4, 'Histórias Visuais', 'Capturar a essência de alguém em uma foto é uma verdadeira arte.', 12),
(5, 'Além da Imagem', 'Gostei muito da sua abordagem sobre retratos.', 12),
(6, 'Beleza Escondida', 'Adoro explorar lugares menos conhecidos da cidade.', 13),
(7, 'A Alma da Cidade', 'Cada beco tem sua história, ótimo texto.', 13),
(8, 'A Voz dos Muros', 'Os grafites realmente falam por aqueles que não têm voz.', 14),
(9, 'Arte e Realidade', 'A arte urbana é um reflexo poderoso da sociedade.', 14),
(10, 'Transformações Pessoais', 'Alguns livros realmente mudam a forma como vemos a vida.', 15),
(1, 'Leitura que Inspira', 'Adorei suas sugestões de leitura, já quero ler todos.', 15),
(2, 'Leitura Terapêutica', 'Ler é o melhor refúgio para a mente e a alma.', 16),
(3, 'Viajando pela Leitura', 'Um bom livro é como uma viagem sem sair de casa.', 16),
(4, 'Lugares dos Sonhos', 'Esses lugares parecem mágicos, já estão na minha lista!', 17),
(5, 'Exploração Única', 'Adorei seu relato, dá vontade de explorar o mundo.', 17),
(6, 'Planejamento Eficiente', 'Essas dicas são essenciais para qualquer viajante.', 18),
(7, 'Organizando Aventuras', 'Obrigado pelas sugestões, serão muito úteis!', 18),
(8, 'Reflexões Cinematográficas', 'O cinema realmente nos faz refletir sobre a vida.', 19),
(9, 'A Arte da Tela', 'Adorei suas observações, o cinema é pura arte.', 19),
(10, 'Criatividade no Cinema', 'Esses filmes realmente despertam criatividade, excelente lista.', 20),
(1, 'Inspiração e Arte', 'A seleção é ótima, adoro filmes que nos motivam.', 20);
