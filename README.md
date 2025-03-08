# ☕ Backend-javacoffee

Este projeto é um backend para uma cafeteria, desenvolvido em Java com Spring Boot. Ele gerencia produtos do cardápio, além de conter funcionalidades de autenticação, registro de usuários, permissões, Docker e testes.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot, JPA & Security**
- **MySQL** (banco de dados)
- **Docker** (para containerização)
- **JUnit** (para testes)

## 📦 Instalação

1. Clone o repositório:
   ```sh
   git clone https://github.com/GabrielSiqueiraGama/backend-javacoffee.git
   cd backend-javacoffee
   ```
2. Configure o banco de dados no arquivo `application.properties`.

## 🛠 Funcionalidades

- CRUD de produtos do cardápio (nome, descricao, preço, categoria, imagem)
- Autenticação de usuários (registro, login, logout)
- Controle de permissões (admin e user)
- Testes 

## 📄 API

### 🛍 Produtos

- **GET** `/cardapio` → Lista todos os produtos
- **POST** `/cardapio` → Cria um novo produto (admin)
- **PUT** `/cardapio/{id}` → Atualiza um produto (admin)
- **DELETE** `/cardapio/{id}` → Remove um produto (admin)

### 🔑 Autenticação

- **POST** `/auth/register` → Registro de novo usuário
- **POST** `/auth/login` → Login e geração de token JWT

## 📌 Licença

Este projeto está sob a licença MIT.

