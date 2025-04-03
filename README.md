# README - Projeto Blog API

## Sobre o Projeto

Este projeto é uma API RESTful para um sistema de blog com funcionalidades de usuários, posts e comentários. A estrutura do projeto  segue o padrão MVC (Model-View-Controller) com algumas adaptações para APIs REST. A API utiliza Spring Boot e oferece endpoints para CRUD (Create, Read, Update, Delete) de todas as entidades, além de recursos de paginação, autenticação e relacionamentos entre entidades.

### Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/blog/
│   │   ├── config/          # Configurações do projeto
│   │   ├── controller/      # Controladores REST
│   │   ├── exception/       # Tratamento de exceções
│   │   ├── mapper/          # Objetos de Transferência de Dados
│   │   ├── model/           # Entidades do banco de dados
│   │   ├── repository/      # Interfaces de acesso a dados
│   │   ├── service/         # Lógica de negócio
│   │   ├── requests/        # Clases para entrada de dados
│   │   ├── response/        # Classes para saidas de dados
│   │   └── util/            # Utilitários
│   └── resources/
│       └── application.properties   # Configurações da aplicação
└── documentacao
```

## Descrição das Principais Classes

### 1. Pacote `model` (Entidades)

Contém as classes que representam as tabelas do banco de dados:

- **Usuario**: Representa um usuário do sistema
  - Campos: id, nome, email, senha (hash), corFoto
  - Relacionamentos: Um-para-muitos com Post e Comentario

- **Post**: Representa uma postagem no blog
  - Campos: id, titulo, texto, dataHora
  - Relacionamentos: Muitos-para-um com Usuario, Um-para-muitos com Comentario

- **Comentario**: Representa um comentário em um post
  - Campos: id, titulo, texto, dataHora
  - Relacionamentos: Muitos-para-um com Usuario e Post

### 2. Pacote `repository` (Acesso a Dados)

Interfaces que estendem JpaRepository para operações CRUD:

- **UsuarioRepository**: Operações com a tabela de usuários
  - Métodos customizados: findByEmail, existsByEmail

- **PostRepository**: Operações com a tabela de posts
  - Métodos customizados: findAllByUsuario, findByTextoContaining

- **ComentarioRepository**: Operações com a tabela de comentários
  - Métodos customizados: findAllByPost, findAllByUsuario

### 3. Pacote `service` (Lógica de Negócio)

Classes que contêm a lógica de negócio da aplicação:

- **UsuarioService**: Gerencia operações de usuário
  - Métodos: criarUsuario, autenticarUsuario, atualizarUsuario, deletarUsuario

- **PostService**: Gerencia operações de postagem
  - Métodos: criarPost, atualizarPost, deletarPost, listarPosts

- **ComentarioService**: Gerencia operações de comentário
  - Métodos: criarComentario, atualizarComentario, deletarComentario, listarComentarios

### 4. Pacote `controller` (Endpoints REST)

Classes que definem os endpoints da API:

- **UsuarioController**: Endpoints relacionados a usuários
  - Rotas: /usuarios, /usuarios/login, /usuarios/{id}

- **PostController**: Endpoints relacionados a posts
  - Rotas: /posts, /posts/{id}, /posts/pagina/{pagina}

- **ComentarioController**: Endpoints relacionados a comentários
  - Rotas: /comentarios, /comentarios/{id}, /comentarios/pagina/{pagina}

### 5. Pacote `dto` (Data Transfer Objects)

Classes para transferência de dados entre camadas:

- **UsuarioDTO**: Representação segura de usuário (sem senha)
- **PostDTO**: Representação de post com informações do autor
- **ComentarioDTO**: Representação de comentário com informações do autor e post
- **Request/Response**: Classes para requisições e respostas específicas

### 6. Pacote `exception` (Tratamento de Erros)

Classes para tratamento de exceções:

- **GlobalExceptionHandler**: Captura e trata exceções globais
- **Custom exceptions**: NotFoundException, UnauthorizedException, etc.

### 7. Pacote `config` (Configurações)

Classes de configuração:

- **SecurityConfig**: Configurações de segurança e autenticação
- **PaginationConfig**: Configurações de paginação padrão
- **SwaggerConfig**: Configuração da documentação da API (se aplicável)

## Fluxo de Requisições

1. **Requisição chega** no Controller
2. **Controller** valida entrada básica e delega para o Service
3. **Service** executa lógica de negócio, usando:
   - **Repository** para acesso a dados
   - **DTOs** para transferência entre camadas
   - **Model** para operações com o banco
4. **Service** retorna dados para o Controller
5. **Controller** formata resposta adequada (sucesso/erro)

## Relacionamentos entre Classes

```
Usuario (1) ↔ (N) Post (1) ↔ (N) Comentario (N) ↔ (1) Usuario
```

- Um **Usuário** pode ter muitos **Posts** e muitos **Comentários**
- Um **Post** pertence a um **Usuário** e pode ter muitos **Comentários**
- Um **Comentário** pertence a um **Usuário** e a um **Post**

## Dependências Principais

- Spring Boot Starter Web (REST API)
- Spring Boot Starter Data JPA (Banco de dados)
- Spring Boot Starter Validation (Validação)
- H2 Database (Banco em memória - desenvolvimento)
- Lombok (Redução de boilerplate code)

## Como Executar

1. Clone o repositório
2. Configure o arquivo `application.properties`
3. Execute a classe principal (`Application.java`)
4. Acesse a API em `http://localhost:8080`

A API está pronta para ser consumida conforme a documentação de endpoints fornecida.