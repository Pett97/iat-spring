# iat-spring

## ⚠️ Aviso Importante

> **Este repositório é estritamente um projeto de estudos e laboratório pessoal.**
>
> O objetivo deste código é explorar, praticar e validar conceitos do ecossistema Java e Spring Framework. **Não é recomendado o uso ou implantação deste código em ambientes de produção**, pois ele não contém todas as validações, camadas de segurança e regras de negócio exigidas em um sistema produtivo de grande porte.

## 📌 Sobre o Projeto

O **iat-spring** foi desenvolvido com o intuito de aplicar na prática arquiteturas backend modernas utilizando **Java** e **Spring Boot**.

Atualmente, atuo profissionalmente com o desenvolvimento e manutenção de sistemas robustos do tipo **ERP**, lidando diariamente com regras de negócio complexas, alta concorrência e integrações críticas. Este projeto serve como meu espaço de testes para experimentação de novas bibliotecas, padrões de projeto (*design patterns*) e integrações do ecossistema Spring fora do escopo do ERP.

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Linguagem:** Java 17+

* **Framework Principal:** Spring Boot 3

* **Persistência / Banco de Dados:** Spring Data JPA / Hibernate

* **Gerenciamento de Dependências:** Maven / Gradle

* **Validação & Utilitários:** Lombok, Bean Validation

* **Boas Práticas:** REST API RESTful, Injeção de Dependências, Arquitetura em Camadas (Controller, Service, Repository).

## 🏗️ Arquitetura e Organização

O projeto segue uma estrutura simplificada  do ecossistema Spring:

```
src/main/java/
 └── com/example/iatspring/
      ├── controller/      # Endpoints REST e controle de requisições
      ├── dto/             # Objetos de transferência de dados (DTOs)
      ├── model/           # Entidades de domínio JPA
      ├── repository/      # Interfaces de acesso ao banco de dados
      └── service/         # Regras de negócio e lógica de aplicação

```

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos

* **Java JDK 17** ou superior instalado

* **Maven** (ou wrapper `./mvnw` incluído no projeto)

* Git

### Passos para execução

1. **Clone o repositório:**

   ```
   git clone https://github.com/Pett97/iat-spring.git
   cd iat-spring
   
   ```

2. **Compile o projeto:**

   ```
   ./mvnw clean package
   
   ```

3. **Execute a aplicação:**

   ```
   ./mvnw spring-boot:run
   
   ```

4. A aplicação estará acessível em `http://localhost:8080`.

## 📬 Contato e Conexão Professional

Estou constantemente aprimorando minhas habilidades em desenvolvimento backend e aberto a novas oportunidades e conexões no mercado Java!

* **GitHub:** [@Pett97](https://github.com/Pett97?utm_source=gemini)


*Desenvolvido por Pett97 para fins de aprendizado e evolução contínua em Java.*