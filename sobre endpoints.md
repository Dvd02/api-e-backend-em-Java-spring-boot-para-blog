# Documentação Completa dos Endpoints do Projeto Blog

## Visão Geral
Este projeto é uma API RESTful para um sistema de blog com funcionalidades de usuários, posts e comentários. A API utiliza Spring Boot e oferece endpoints para CRUD (Create, Read, Update, Delete) de todas as entidades, além de recursos de paginação, autenticação e relacionamentos entre entidades.

## Autenticação
Todos os endpoints que modificam dados (POST, PUT, DELETE) requerem autenticação através de email e senha no corpo da requisição.

---

## Índice de Endpoints

### 1. Endpoints de Usuário

1.2 CRUD de Usuários
- [POST /usuarios](#post-usuarios)
- [GET /usuarios/{id}](#get-usuariosid)
- [PUT /usuarios](#put-usuarios)
- [DELETE /usuarios](#delete-usuarios)

1.2 Filtros de Usuários
- [GET /usuarios/pagina/{pagina}/tamanho/{tamanho}/chamado/{nomeUsuario}](#get-usuariospaginapaginatamanhotamanhochamadonomeusuario)

1.3 Autenticação
- [POST /usuarios/login](#post-usuarioslogin)

### 2. Endpoints de Post
2.1 CRUD de Posts
- [POST /posts](#post-posts)
- [GET /posts/pagina/{pagina}/tamanho/{tamanho}](#get-postspaginapaginatamanhotamanho)
- [GET /posts/{id}](#get-postsid)
- [PUT /posts](#put-posts)
- [DELETE /posts](#delete-posts)

2.2 Filtros de Posts
- [GET /posts/pagina/{pagina}/tamanho/{tamanho}/do_usuario_chamado/{nomeUsuario}](#get-postspaginapaginatamanhotamanhodo_usuario_chamadonomeusuario)
- [GET /posts/pagina/{pagina}/tamanho/{tamanho}/do_usuario_id/{idUsuario}](#get-postspaginapaginatamanhotamanhodo_usuario_ididusuario)
- [GET /posts/pagina/{pagina}/tamanho/{tamanho}/com/{texto}](#get-postspaginapaginatamanhotamanhocomtexto)

### 3. Endpoints de Comentário
3.1 CRUD de Comentários
- [POST /comentarios](#post-comentarios)
- [GET /comentarios/{id}](#get-comentariosid)
- [PUT /comentarios](#put-comentarios)
- [DELETE /comentarios](#delete-comentarios)

3.2 Filtros de Comentários
- [GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/do_post_id/{idPost}](#get-comentariospaginapaginatamanhotamanhodo_post_ididpost)
- [GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/do_usuario_id/{idUsuario}](#get-comentariospaginapaginatamanhotamanhodo_usuario_ididusuario)
- [GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/do_usuario_chamado/{nomeUsuario}](#get-comentariospaginapaginatamanhotamanhodo_usuario_chamadonomeusuario)
- [GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/com/{texto}](#get-comentariospaginapaginatamanhotamanhocomtexto)
- [GET /comentarios/pagina/{pagina}/tamanho/{tamanho}](#get-comentariospaginapaginatamanhotamanho)

---

## Detalhamento dos Endpoints

### Endpoints de Usuário

#### POST /usuarios/login
**Autentica um usuário e retorna seu ID**

**Corpo da Requisição:**
```json
{
  "email": "string",
  "senha": "string"
}
```

**Resposta de Sucesso:**
```json
{
  "idUsuario": 1,
  "isError": false
}
```

**Possíveis Erros:**
- 401 Unauthorized - Credenciais inválidas
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "Usuário não encontrado com o email: email@invalido.com",
  "details": "uri=/usuarios/login",
  "isError": true
}
```

#### POST /usuarios
**Cria um novo usuário**

**Corpo da Requisição:**
```json
{
  "nome": "string",
  "email": "string",
  "senha": "string",
  "corFoto": "string (opcional, padrão: 808080)"
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O usuario (ID: 1) foi criado"
}
```

**Possíveis Erros:**
- 400 Bad Request - Email já existe ou dados inválidos
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "Já existe um usuário com este email",
  "details": "uri=/usuarios",
  "isError": true
}
```

#### GET /usuarios/pagina/{pagina}/tamanho/{tamanho}/chamado/{nomeUsuario}
**Lista usuários paginados com filtro por nome**

**Parâmetros:**
- `pagina`: Número da página (começa em 1)
- `tamanho`: Quantidade de itens por página
- `nomeUsuario`: Termo para filtrar por nome (case insensitive)

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "conteudo": [
    {
      "id": 1,
      "nome": "Usuário 1",
      "corfoto": "808080"
    }
  ],
  "temProximaPagina": true,
  "numeroDaPagina": 1,
  "tamanhoDaPagina": 10
}
```

#### GET /usuarios/{id}
**Busca usuário por ID**

**Resposta de Sucesso:**
```json
{
  "id": 1,
  "nome": "Usuário 1",
  "corfoto": "808080"
}
```

**Possíveis Erros:**
- 404 Not Found - Quando o usuário não existe
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "O usuario (ID:999) não existe",
  "details": "uri=/usuarios/999",
  "isError": true
}
```

#### PUT /usuarios
**Atualiza dados do usuário (requer autenticação)**

**Corpo da Requisição:**
```json
{
  "emailAntigo": "string",
  "senhaAntiga": "string",
  "emailNovo": "string",
  "senhaNova": "string",
  "nomeNovo": "string",
  "corFoto": "string"
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O usuario (ID: 1) foi editado"
}
```

#### DELETE /usuarios
**Remove um usuário (requer autenticação)**

**Corpo da Requisição:**
```json
{
  "email": "string",
  "senha": "string"
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O usuario (ID: 1) foi deletado"
}
```

---

### Endpoints de Post

#### POST /posts
**Cria um novo post (requer autenticação)**

**Corpo da Requisição:**
```json
{
  "email": "string",
  "senha": "string",
  "titulo": "string",
  "texto": "string"
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O post (ID: 1) foi criado."
}
```

#### GET /posts/pagina/{pagina}/tamanho/{tamanho}
**Lista posts paginados**

**Parâmetros:**
- `pagina`: Número da página
- `tamanho`: Itens por página

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "conteudo": [
    {
      "id": 1,
      "titulo": "Post 1",
      "texto": "Texto do post 1",
      "dataehora": "2023-01-01T00:00:00",
      "usuario": {
        "id": 1,
        "nome": "Usuário 1",
        "corfoto": "808080"
      }
    }
  ],
  "temProximaPagina": true,
  "numeroDaPagina": 1,
  "tamanhoDaPagina": 10
}
```

#### GET /posts/{id}
**Busca post por ID**

**Resposta de Sucesso:**
```json
{
  "id": 1,
  "titulo": "Post 1",
  "texto": "Texto do post 1",
  "dataehora": "2023-01-01T00:00:00",
  "usuario": {
    "id": 1,
    "nome": "Usuário 1",
    "corfoto": "808080"
  }
}
```

**Possíveis Erros:**
- 404 Not Found - Quando o post não existe
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "O post (ID: 999) não existe.",
  "details": "uri=/posts/999",
  "isError": true
}
```

#### PUT /posts
**Atualiza um post (requer autenticação e ser dono do post)**

**Corpo da Requisição:**
```json
{
  "id": 1,
  "email": "string",
  "senha": "string",
  "titulo": "string",
  "texto": "string"
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O post (ID: 1) foi editado."
}
```

**Possíveis Erros:**
- 403 Forbidden - Usuário não é dono do post
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "O post não pertence ao usuário.",
  "details": "uri=/posts",
  "isError": true
}
```

#### DELETE /posts
**Remove um post (requer autenticação e ser dono do post)**

**Corpo da Requisição:**
```json
{
  "email": "string",
  "senha": "string",
  "id": 1
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O post (ID: 1) foi deletado."
}
```

#### GET /posts/pagina/{pagina}/tamanho/{tamanho}/do_usuario_chamado/{nomeUsuario}
**Filtra posts por nome de usuário**

#### GET /posts/pagina/{pagina}/tamanho/{tamanho}/do_usuario_id/{idUsuario}
**Filtra posts por ID de usuário**

#### GET /posts/pagina/{pagina}/tamanho/{tamanho}/com/{texto}
**Filtra posts contendo texto específico**

---

### Endpoints de Comentário

#### POST /comentarios
**Cria um novo comentário em um post (requer autenticação)**

**Corpo da Requisição:**
```json
{
  "email": "string",
  "senha": "string",
  "titulo": "string",
  "texto": "string",
  "idPost": 1
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O comentario (ID: 1) foi criado."
}
```

#### GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/do_post_id/{idPost}
**Lista comentários paginados de um post específico**

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "conteudo": [
    {
      "id": 1,
      "titulo": "Comentário 1",
      "texto": "Texto do comentário 1",
      "dataehora": "2023-01-01T00:00:00",
      "usuario": {
        "id": 1,
        "nome": "Usuário 1",
        "corfoto": "808080"
      },
      "post": {
        "id": 1
      }
    }
  ],
  "temProximaPagina": true,
  "numeroDaPagina": 1,
  "tamanhoDaPagina": 10
}
```

#### GET /comentarios/{id}
**Busca comentário por ID**

**Resposta de Sucesso:**
```json
{
  "id": 1,
  "titulo": "Comentário 1",
  "texto": "Texto do comentário 1",
  "dataehora": "2023-01-01T00:00:00",
  "usuario": {
    "id": 1,
    "nome": "Usuário 1",
    "corfoto": "808080"
  },
  "post": {
    "id": 1
  }
}
```

**Possíveis Erros:**
- 404 Not Found - Quando o comentário não existe
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "O comentario (ID:999) não existe",
  "details": "uri=/comentarios/999",
  "isError": true
}
```

#### PUT /comentarios
**Atualiza um comentário (requer autenticação e ser dono do comentário)**

**Corpo da Requisição:**
```json
{
  "id": 1,
  "email": "string",
  "senha": "string",
  "titulo": "string",
  "texto": "string"
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O comentario (ID: 1) foi editado."
}
```

#### DELETE /comentarios
**Remove um comentário (requer autenticação e ser dono do comentário)**

**Corpo da Requisição:**
```json
{
  "email": "string",
  "senha": "string",
  "id": 1
}
```

**Resposta de Sucesso:**
```json
{
  "isError": false,
  "message": "O comentario (ID: 1) foi deletado."
}
```

#### GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/do_usuario_id/{idUsuario}
**Filtra comentários por ID de usuário**

#### GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/do_usuario_chamado/{nomeUsuario}
**Filtra comentários por nome de usuário**

#### GET /comentarios/pagina/{pagina}/tamanho/{tamanho}/com/{texto}
**Filtra comentários contendo texto específico**

#### GET /comentarios/pagina/{pagina}/tamanho/{tamanho}
**Lista todos os comentários paginados**

---

## Tratamento de Erros

### Códigos de Status HTTP e Mensagens:

1. **400 Bad Request**:
   - Dados inválidos na requisição
   - Parâmetros de paginação inválidos (devem ser >= 1)
   - Formato de dados inválido

2. **401 Unauthorized**:
   - Credenciais inválidas no login
   - Falha na autenticação

3. **403 Forbidden**:
   - Usuário não tem permissão para modificar o recurso (não é o dono)

4. **404 Not Found**:
   - Recurso não encontrado (ID inválido)

5. **500 Internal Server Error**:
   - Erro inesperado no servidor

### Formato de Resposta de Erro
Todos os erros seguem o padrão:
```json
{
  "timestamp": "2023-01-01T00:00:00.000+00:00",
  "message": "Mensagem de erro descritiva",
  "details": "uri=/endpoint",
  "isError": true
}
```

## Considerações Finais
- Todos os endpoints que modificam dados requerem autenticação via email/senha
- A paginação está disponível para listagens, com controle de página e tamanho
- As respostas seguem um padrão com indicação de sucesso/erro e mensagens descritivas
- Os relacionamentos entre entidades (usuário-post-comentário) são mantidos e refletidos nas respostas
- A API utiliza DTOs (Data Transfer Objects) para formatar as respostas, omitindo dados sensíveis como senhas