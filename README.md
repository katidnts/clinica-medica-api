# 🏥 API REST – Clínica Médica

API REST desenvolvida em **Java + Spring Boot**, voltada para o gerenciamento de médicos em uma clínica médica.  
O projeto foi construído com foco em **boas práticas, validação robusta, arquitetura limpa e modelagem consistente**, representando um exemplo realista de aplicação backend corporativa.

---

## 🚀 Objetivo do Projeto

Este projeto demonstra domínio dos principais conceitos usados no desenvolvimento de APIs modernas com Spring Boot, incluindo:

- Arquitetura em camadas  
- DTOs para entrada e saída de dados  
- Validação estruturada com Bean Validation  
- Persistência com JPA/Hibernate  
- Paginação automática com Pageable  
- Modelagem clara de entidades e objetos de valor  
- Regras de negócio desacopladas do controller  

---

## 🧩 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **Bean Validation**
- **DTOs (Records)**

> O projeto não utiliza autenticação JWT nem Flyway, mantendo o foco no backend core.  
> A estrutura permite expansão futura.

---

## 📚 Funcionalidades Implementadas

- Cadastro de médicos e pacientes
- Listagem paginada e ordenada  
- Detalhamento de médico e paciente por ID  
- Atualização de dados (com regra para impedir alteração do CRM e do CPF do paciente)  
- Inativação lógica (soft delete)  
- Modelo de endereço como **Embeddable**  
- Validação automática de campos  
- Estrutura clara de DTOs  
---

## 🛠️ Variáveis de Ambiente

Antes de executar o projeto, configure as seguintes variáveis de ambiente:

| Variável | Descrição |
|---------|-----------|
| `DB_URL` | URL completa de conexão com o PostgreSQL (`jdbc:postgresql://host:porta/banco`) |
| `DB_NAME` | Usuário do banco de dados |
| `DB_PASSWORD` | Senha do banco de dados |

### Windows (PowerShell)
```powershell
setx DB_URL "jdbc:postgresql://localhost:5432/clinica"
setx DB_NAME "postgres"
setx DB_PASSWORD "sua_senha"
```

```Linux/macOS
export DB_URL="jdbc:postgresql://localhost:5432/clinica"
export DB_NAME="postgres"
export DB_PASSWORD="sua_senha"

```
---

## 🧪 Collection Postman

O repositório contém uma **collection completa do Postman**, facilitando testes imediatos da API.

Arquivo disponível em:
/postman/clinica-medica.postman_collection.json


Basta importar no Postman.

---

# 📌 **Endpoints da API**

Abaixo estão todos os endpoints organizados, com exemplos de requisição e resposta.


## 🩺 Médico

---

## 📍 **1. Cadastrar médico**

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
### 📥 Response(201)
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
---

## 📍 **2. Listar médicos (paginação)**

**GET** `/medicos?page=0&size=10&sort=nome`

### 📥 Response(200)

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

## 📍 **3. Detalhar médico**

**GET** `/medicos/{id}`

### 📥 Response(200)
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
## 📍 **4. Atualizar médico**

**PATCH** `/medicos/{id}`

### 📤 Request body

```
{
  "telefone": "11900001111"
}

```
### 📥 Response(200)

```
{
    "id": null,
    "nome": null,
    "email": null,
    "telefone": "11900001111",
    "crm": null,
    "especialidade": null,
    "endereco": null
}
```
---

## 📍 **5. Inativar médico**

**DELETE** `/medicos/{id}`

### 📥 Response(204)


## 🧍 Paciente


## 📍 **1. Cadastrar paciente**

**POST** `/pacientes`

### 📤 Request body

```
{
  "nome": "João Pereira",
  "email": "joao.pereira@mail.com",
  "cpf": "12345678900",
  "telefone": "11988887777",
  "endereco": {
    "logradouro": "Rua Central",
    "bairro": "Centro",
    "cep": "12345000",
    "cidade": "São Paulo",
    "uf": "SP",
    "numero": "150",
    "complemento": "Apto 12"
  }
}

```
### 📥 Response(201)

```
{
    "id": 5,
    "nome": "João Pereira",
    "email": "joao.pereira@mail.com",
    "telefone": "11988887777",
    "endereco": {
        "logradouro": "Rua Central",
        "numero": "150",
        "complemento": "Apto 12",
        "bairro": "Centro",
        "cep": "12345000",
        "cidade": "São Paulo",
        "uf": "SP"
    }
}
```

## 📍 **2. Listar pacientes (paginação)**

**GET** `/pacientes?page=0&size=10&sort=nome`

### 📥 Response(200)

```
{
  "content": [
    {
      "id": 1,
      "nome": "João Pereira",
      "email": "joao.pereira@mail.com",
      "telefone": "11988887777",
      "cpf": "12345678900"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 10
}

```


## 📍 **3. Detalhar paciente**

**GET** `/pacientes/{id}`

### 📥 Response(200)

```
{
    "id": 5,
    "nome": "João Pereira",
    "email": "joao.pereira@mail.com",
    "telefone": "11988887777",
    "endereco": {
        "logradouro": "Rua Central",
        "numero": "150",
        "complemento": "Apto 12",
        "bairro": "Centro",
        "cep": "12345000",
        "cidade": "São Paulo",
        "uf": "SP"
    }
}
```
## 📍 **4. Atualizar paciente**

**PATCH** `/pacientes/{id}`

### 📤 Request body

```
{
    "nome": "João Pereira Neto"
 }
```

### 📥 Response(200)

```
{
    "id": null,
    "nome": "João Pereira Neto",
    "email": null,
    "telefone": null,
    "endereco": null
}
```


## 📍 **5. Inativar paciente**

**DELETE** `/pacientes/{id}`

### 📥 Response(204)
