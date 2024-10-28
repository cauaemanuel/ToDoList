
# Projeto Todo List API

Este projeto é uma API de gerenciamento de tarefas que permite a criação, listagem, e atualização de tarefas, além da criação de usuários. A API utiliza autenticação básica para proteger os endpoints de criação e atualização de tarefas.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **MapStruct** (para mapeamento de DTOs)
- **Banco de dados**: Configurável (MySQL, H2, ou outro)

## Endpoints da API

### 1. Usuários

#### Criar Usuário

- **Endpoint**: `/users/`
- **Método**: `POST`
- **Descrição**: Cria um novo usuário que pode autenticar-se e acessar a API.
- **Autenticação**: Nenhuma

- **Request Body**:
  ```json
  {
    "name": "caua",
    "username": "cauazin",
    "password": "123"
  }
  ```

- **Exemplo de Resposta (201 Created)**:
  ```json
  {
    "username": "cauazin",
    "name": "caua",
    "password": "$2a$12$XmrQI.UkPJ.I1s/0gvYzKO3n7tOZebvkNSM/JBKBQsD7Mw5SoPWa6",
    "createAt": "2024-10-28T13:34:28.963594",
    "id": 1
  }
  ```

### 2. Tarefas

#### Listar Todas as Tarefas

- **Endpoint**: `/task/`
- **Método**: `GET`
- **Descrição**: Retorna uma lista de todas as tarefas cadastradas.
- **Autenticação**: Nenhuma

#### Criar Tarefa

- **Endpoint**: `/task/`
- **Método**: `POST`
- **Descrição**: Cria uma nova tarefa.
- **Autenticação**: Necessária (Basic Auth)

- **Request Body**:
  ```json
  {
    "description": "Descrição da tarefa",
    "title": "Título da tarefa",
    "priority": "alta",
    "startAt": "2024-11-15T10:00:00",
    "endAt": "2024-12-15T12:00:00"
  }
  ```

- **Exemplo de Resposta (201 Created)**:
  ```json
  {
    "id": 1,
    "description": "Descrição da tarefa",
    "title": "Título da tarefa",
    "startAt": "2024-11-15T10:00:00",
    "endAt": "2024-12-15T12:00:00",
    "priority": "alta",
    "createdAt": "2024-10-28T13:35:39.663377",
    "userID": id do Usuario passado na autenticação
  }
  ```

#### Atualização Parcial de Tarefa

- **Endpoint**: `/task/{idTask}`
- **Método**: `PUT`
- **Descrição**: Atualiza parcialmente uma tarefa existente, com base no ID fornecido.
- **Autenticação**: Necessária (Basic Auth)

- **Request Body (exemplo)**:
  ```json
  {
    "description": "Nova descrição da tarefa"
  }
  ```

- **Exemplo de Resposta (200 OK)**:
  ```json
   {
    "id": 1,
    "description": "Nova Descrição da tarefa",
    "title": "Título da tarefa",
    "startAt": "2024-11-15T10:00:00",
    "endAt": "2024-12-15T12:00:00",
    "priority": "alta",
    "createdAt": "2024-10-28T13:35:39.663377",
    "userID": id do Usuario passado na autenticação
  }
  ```

## Autenticação

Para acessar os endpoints protegidos (`/tasks` e `/task/{idTask}`), é necessário autenticar-se com o nome de usuário e senha do usuário criado previamente.

- **Autenticação**: Basic Auth
  - **Formato**: Envie o nome de usuário e a senha no cabeçalho da requisição em `Authorization: Basic <token>`, onde `<token>` é a string codificada em Base64 do `username:password`.


