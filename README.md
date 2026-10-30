<div align="center">

# S P R I N G　B O O T　L I S T

### `JAVA • SPRING BOOT • MVC • CRUD • CLOUD`

**Sistema web de cadastro e gerenciamento de produtos**

![Java](https://img.shields.io/badge/JAVA-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SPRING_BOOT-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MYSQL-COMPATIBLE-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/DOCKER-READY-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Render](https://img.shields.io/badge/RENDER-DEPLOYED-111827?style=for-the-badge)

### [ ◉ ACESSAR SISTEMA ONLINE ](https://springbootlist.onrender.com/products)

</div>

---

## `PROJECT // VISÃO GERAL`

O **springBootList** é uma aplicação web desenvolvida em **Java 17 com Spring Boot** para gerenciar um cadastro de produtos. O projeto faz parte dos estudos de desenvolvimento backend e aplica o padrão **MVC**, separando interface, requisições HTTP, regras de negócio e persistência dos dados.

A interface usa **Thymeleaf** para renderizar páginas HTML no servidor, enquanto o **Spring Data JPA** realiza as operações com o banco relacional. A aplicação é empacotada com **Docker**, publicada no **Render** e conectada ao **TiDB Cloud**, compatível com MySQL.

---

## `SYSTEM // STATUS`

| Componente | Situação |
| :--- | :---: |
| Listagem de produtos | `● IMPLEMENTADO` |
| Cadastro de produtos | `● IMPLEMENTADO` |
| Edição de produtos | `● IMPLEMENTADO` |
| Exclusão de produtos | `● IMPLEMENTADO` |
| Validação de formulário | `● IMPLEMENTADO` |
| Integração com banco de dados | `● CONFIGURADO` |
| Docker | `● CONFIGURADO` |
| Hospedagem no Render | `● PUBLICADO` |

> **STATUS:** o deploy foi concluído com sucesso no Render. A disponibilidade do serviço pode variar conforme a infraestrutura da hospedagem gratuita.

---

## `CRUD CORE // FUNCIONALIDADES`

| Operação | O que faz | Acesso |
| --- | --- | --- |
| **Create** | Cadastra novos produtos | `/products/new` |
| **Read** | Exibe os produtos cadastrados | `/products` |
| **Update** | Abre o formulário de edição e salva alterações | `/products/edit/{id}` |
| **Delete** | Remove um produto pelo identificador | `/products/delete/{id}` |

Cada produto possui **nome, marca, país de fabricação e preço**, além de um **ID** gerado no banco.

O sistema valida dados recebidos pelos formulários usando **Jakarta Bean Validation**, incluindo campos obrigatórios e preço maior que zero. Após salvar ou excluir um registro, a aplicação redireciona para a listagem e apresenta uma mensagem de sucesso.

---

## `ARCHITECTURE // FLUXO MVC`

```text
                 ┌────────────────────┐
                 │      NAVEGADOR     │
                 │   HTML / FORMULÁRIO│
                 └──────────┬─────────┘
                            │ HTTP
                            ▼
                 ┌────────────────────┐
                 │  ProductController │
                 │     Spring MVC     │
                 └──────────┬─────────┘
                            │ DTO
                            ▼
                 ┌────────────────────┐
                 │   ProductService   │
                 │ Regras / Operações │
                 └──────────┬─────────┘
                            │ Entidade
                            ▼
                 ┌────────────────────┐
                 │ ProductRepository  │
                 │   Spring Data JPA  │
                 └──────────┬─────────┘
                            │ SQL
                            ▼
                 ┌────────────────────┐
                 │     TiDB Cloud     │
                 │ MySQL Compatible   │
                 └────────────────────┘
```

O **Controller** recebe as requisições e escolhe a página. O **Service** concentra as operações sobre os produtos. O **Repository** acessa os dados usando JPA. Os **DTOs** definem os dados que entram e saem da aplicação, enquanto o **Thymeleaf** monta as páginas HTML.

---

## `TECH // TECNOLOGIAS`

| Backend | Interface | Dados e infraestrutura |
| :--- | :--- | :--- |
| Java 17 | HTML | TiDB Cloud / MySQL |
| Spring Boot 4.1.1 | Thymeleaf | Spring Data JPA |
| Spring Web MVC | Bootstrap | Hibernate |
| Jakarta Validation | Templates server-side | Docker |
| Maven | | Render |
| Lombok | | Git / GitHub |

---

## `STRUCTURE // ORGANIZAÇÃO DO PROJETO`

```text
springBootList/
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── database/
│   └── schema.sql
└── src/main/
    ├── java/com/lab/mvc/springbootlist/
    │   ├── SpringBootListApplication.java
    │   ├── controller/
    │   │   └── ProductController.java
    │   ├── dto/
    │   │   ├── ProductRequestDTO.java
    │   │   └── ProductResponseDTO.java
    │   ├── model/
    │   │   └── Product.java
    │   ├── repository/
    │   │   └── ProductRepository.java
    │   └── service/
    │       └── ProductService.java
    └── resources/
        ├── application.properties
        └── templates/
            ├── index.html
            ├── new_product.html
            ├── edit_product.html
            ├── alert.html
            └── validacao.html
```

---

## `ROUTES // ENDPOINTS WEB`

| Método | Rota | Responsabilidade |
| :---: | --- | --- |
| `GET` | `/products` | Exibir a lista de produtos |
| `GET` | `/products/new` | Abrir o formulário de cadastro |
| `POST` | `/products/save` | Salvar um cadastro ou uma edição |
| `GET` | `/products/edit/{id}` | Abrir o formulário de edição |
| `GET` | `/products/delete/{id}` | Excluir o produto e voltar à listagem |

> **Observação técnica:** este é um projeto MVC com páginas renderizadas no servidor, não uma API REST JSON. A exclusão usa `GET` conforme a implementação da atividade; em um sistema de produção, o ideal seria empregar um método HTTP com semântica de alteração, como `POST` ou `DELETE`, acompanhado de proteção CSRF.

---

## `DATABASE // PERSISTÊNCIA`

O banco da aplicação utiliza o esquema **`sales`** com a tabela **`product`**.

| Coluna | Tipo | Descrição |
| --- | --- | --- |
| `id` | `BIGINT` | Identificador e chave primária |
| `nome` | `VARCHAR(60)` | Nome do produto |
| `marca` | `VARCHAR(45)` | Marca |
| `pais_fabricacao` | `VARCHAR(60)` | País de fabricação |
| `preco` | `DECIMAL(10,2)` | Valor do produto |

O script de criação está em [`database/schema.sql`](database/schema.sql). A configuração utiliza `spring.jpa.hibernate.ddl-auto=validate`, portanto a estrutura do banco deve existir antes da inicialização da aplicação.

---

## `BOOT SEQUENCE // EXECUÇÃO LOCAL`

**Pré-requisitos:** JDK 17, acesso a um banco compatível com MySQL e conexão para baixar as dependências Maven na primeira execução.

```bash
git clone https://github.com/gilbertocorrea-cmd/springBootList.git
cd springBootList
```

Configure as variáveis de ambiente no terminal antes de executar (exemplo para Linux):

```bash
export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/sales'
export SPRING_DATASOURCE_USERNAME='seu_usuario'
export SPRING_DATASOURCE_PASSWORD='sua_senha'
```

Crie o banco e a tabela com `database/schema.sql` usando seu cliente SQL e depois inicie o servidor:

```bash
./mvnw spring-boot:run
```

Acesse **http://localhost:8080/products**.

Para gerar o arquivo executável:

```bash
./mvnw clean package
java -jar target/*.jar
```

---

## `ENV CONFIG // VARIÁVEIS DE AMBIENTE`

| Variável | Finalidade |
| --- | --- |
| `SPRING_DATASOURCE_URL` | URL JDBC do banco MySQL/TiDB |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |
| `PORT` | Porta HTTP fornecida pelo ambiente de hospedagem |

As credenciais devem permanecer nas configurações do ambiente, nunca em commits ou no README. O arquivo `application.properties` lê as três variáveis de conexão.

---

## `DEPLOY // PRODUÇÃO`

```text
                GITHUB / MAIN
                      │
                      ▼
                RENDER / BUILD
                      │
                      ▼
               DOCKER MULTISTAGE
                      │
                ┌─────┴──────┐
                │            │
                ▼            ▼
           MAVEN BUILD    JAVA 17 JRE
                             │
                             ▼
                     SPRING BOOT MVC
                             │
                             ▼
                        TiDB CLOUD
```

O `Dockerfile` compila o projeto usando Maven e executa o JAR em uma imagem com Java 17, com usuário sem privilégios administrativos. O servidor utiliza a variável `PORT` fornecida pelo Render.

O Render está conectado à branch **`main`** com **Auto-Deploy (`On Commit`)**: novos commits enviados ao GitHub iniciam uma nova publicação automaticamente.

**Aplicação:** https://springbootlist.onrender.com/products

---

## `LEARNING // CONCEITOS PRATICADOS`

- Organização de uma aplicação Java em camadas.
- Arquitetura **MVC** e requisições HTTP.
- Persistência de dados com **JPA / Hibernate**.
- Uso de **DTOs com Java Records**.
- Validação dos campos do formulário.
- Injeção de dependências e transações no Spring.
- Renderização de HTML com Thymeleaf.
- Docker, variáveis de ambiente e publicação em nuvem.

---

<div align="center">

## `SPRING BOOT LIST // SYSTEM ONLINE`

**Gilberto Corrêa**  
Análise e Desenvolvimento de Sistemas — FATEC

`JAVA` • `SPRING BOOT` • `MVC` • `JPA` • `DOCKER` • `RENDER`

**● APPLICATION DEPLOYED**

</div>
