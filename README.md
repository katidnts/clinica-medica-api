## 🏥 Clínica Médica API


![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![JWT](https://img.shields.io/badge/Auth-JWT-orange)

## 📌 Descrição

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de uma clínica médica, permitindo o **cadastro de médicos e pacientes**, além do **agendamento e cancelamento de consultas**.

O projeto foi desenvolvido com foco em **boas práticas de desenvolvimento backend**, utilizando:

* Arquitetura em camadas
* Autenticação com **JWT**
* Versionamento de banco com **Flyway**
* **Documentação automática da API**

---

# 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **Spring Security**
* **JWT (JSON Web Token)**
* **Flyway** (versionamento de banco de dados)
* **Swagger / OpenAPI** (documentação da API)
* **PostgreSQL**
* **Bean Validation**
* **Maven**

---

# 🎯 Objetivo do Projeto

Este projeto foi desenvolvido como parte do aprofundamento em desenvolvimento backend com **Java e Spring Boot, com foco na construção de APIs REST** seguindo boas práticas de arquitetura, segurança e persistência de dados, incluindo:

* Arquitetura em camadas
* **DTO Pattern** para comunicação com a API
* Validação robusta de dados
* Persistência com **JPA/Hibernate**
* Paginação automática com **Spring Data**
* Autenticação segura com **JWT**
* Versionamento de banco com **Flyway**
* Documentação automática da API com **Swagger**
* Implementação de **regras de negócio para agendamento de consultas**

---

# 🧩 Arquitetura do Projeto

A aplicação segue uma **arquitetura em camadas** para melhor organização e manutenção do código.

## Camadas da aplicação

```text
Controller → Camada responsável pelas requisições HTTP
Service → Contém regras de negócio
Repository → Camada de acesso ao banco de dados
Domain → Entidades e DTOs da aplicação
```

Essa separação permite:

* **Maior desacoplamento entre as camadas**
* **Melhor testabilidade**
* **Código mais organizado e manutenível**

## Fluxo de requisição na aplicação

```text  
Client  
↓  
Controller  
↓  
Service (regras de negócio)  
↓  
Repository  
↓  
Database (PostgreSQL)
```

---

# 📚 Funcionalidades Implementadas

## 👨‍⚕️ Médicos

* Cadastro de médicos
* Listagem paginada
* Detalhamento por ID
* Atualização parcial de dados
* Inativação lógica (**soft delete**)

---

## 🧍 Pacientes

* Cadastro de pacientes
* Listagem paginada
* Detalhamento por ID
* Atualização parcial de dados
* Inativação lógica (**soft delete**)

---

## 📅 Consultas

* Agendamento de consultas
* Cancelamento de consultas
* Aplicação de regras de negócio no agendamento
* Validação de dados da consulta

---

## 🔐 Segurança

* Autenticação utilizando **JWT**
* Proteção de endpoints com **Spring Security**

---

## 🗄️ Banco de Dados

* Persistência com **PostgreSQL**
* Versionamento de banco utilizando **Flyway**
* Migrações executadas automaticamente na inicialização da aplicação

---

## 📖 Documentação da API

* Documentação automática com **Swagger / OpenAPI**
* Interface interativa para teste dos endpoints

---

# ⚙️ Como Executar o Projeto

## 1️⃣ Clonar o repositório

```bash
git clone https://github.com/katidnts/clinica-medica-api.git
```

---

## 2️⃣ Configurar variáveis de ambiente

A aplicação utiliza **variáveis de ambiente** para configuração do banco de dados e autenticação.

### Variáveis necessárias

| Variável    | Descrição                               |
| ----------- | --------------------------------------- |
| DB_URL      | URL de conexão com o PostgreSQL         |
| DB_USERNAME     | Usuário do banco                        |
| DB_PASSWORD | Senha do banco                          |
| JWT_SECRET  | Chave secreta para geração do token JWT |

---

### Windows (PowerShell)

```powershell
setx DB_URL "jdbc:postgresql://localhost:5432/clinica"
setx DB_USERNAME "postgres"
setx DB_PASSWORD "sua_senha"
setx JWT_SECRET "seu_token_secreto"
```

---

### Linux / macOS

```bash
export DB_URL="jdbc:postgresql://localhost:5432/clinica"
export DB_USERNAME="postgres"
export DB_PASSWORD="sua_senha"
export JWT_SECRET="seu_token_secreto"
```

---

## 3️⃣ Executar a aplicação

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

# 📖 Documentação da API

Após iniciar a aplicação, acesse a interface do **Swagger**:

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

Essa interface permite:

* Visualizar todos os endpoints da API
* Testar requisições diretamente pelo navegador

# 🔗 Principais Endpoints


**1. Cadastrar médico**

**POST** `/medicos`

### 📤 Request body
```json
{
  "nome": "Maria Silva",
  "email": "maria.silva@clinica.com",
  "crm": "123456",
  "telefone": "11999990000",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "cep": "12345000",
    "cidade": "São Paulo",
    "uf": "SP",
    "numero": "100",
    "complemento": "Sala 20"
  }
}
```
### 📥 Response (201)

```
{
  "id": 1,
 "nome": "Maria Silva",
    "email": "maria.silva@clinica.com",
    "telefone": "11999990000",
    "crm": "112233",
    "especialidade": "CARDIOLOGIA",
    "endereco": {
        "logradouro": "Rua das Flores",
        "numero": "100",
        "complemento": "Sala 20",
        "bairro": "Centro",
        "cep": "12345000",
        "cidade": "São Paulo",
        "uf": "SP"
}
```
## 📍 **2. Listar médicos (paginação)**

**GET** `/medicos?page=0&size=10&sort=nome`

### 📥 Response (200)

```
{
  "content": [
    {
      "id": 1,
      "nome": "Maria Silva",
      "email": "maria.silva@clinica.com",
      "crm": "123456",
      "especialidade": "CARDIOLOGIA"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 10
}
```

## 📍 **3. Agendar consulta**

**POST** `/consultas`

### 📤 Request body

```json
{  
"idPaciente": 4,  
"especialidade": "CARDIOLOGIA",  
"data": "2026-06-06T16:00"  
}

```

## 📍 **4. Cancelar consulta**


**PATCH** `/consultas/{id}`

### 📤 Request body

```json
{  
"motivo": "PACIENTE_DESISTIU"  
}
```


---

# 🧪 Testando a API

O repositório contém uma **collection do Postman** com todos os endpoints configurados.

### Arquivo disponível em

```text
/postman/clinica-medica.postman_collection.json
```

### Como usar

1. Abrir o **Postman**
2. Importar a collection
3. Executar as requisições da API

---

# 📦 Estrutura do Projeto

```text
src
├── controller
├── domain
│   ├── medico
│   ├── paciente
│   ├── consulta
│   └── endereco
├── repository
├── service
├── infra
└── resources
```

---

# 🔮 Melhorias Futuras

Possíveis evoluções do projeto:

* Containerização com **Docker**
* Pipeline **CI/CD com GitHub Actions**
* Deploy em ambiente **cloud**
* Sistema de **notificações de consultas**

---

# 👩‍💻 Autora

**Kati Dantas**

* GitHub: https://github.com/katidnts