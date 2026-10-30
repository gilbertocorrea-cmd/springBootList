# springBootList — guia da estrutura inicial

Este projeto é a base de uma aplicação web de estudo em **Java 17 e Spring Boot**. O objetivo da atividade é desenvolver, ao longo das aulas, um cadastro de produtos com as operações CRUD:

| Operação | Significado | Exemplo do que será desenvolvido |
| --- | --- | --- |
| Create | Criar | Cadastrar um produto. |
| Read | Consultar | Listar os produtos cadastrados. |
| Update | Atualizar | Editar os dados de um produto. |
| Delete | Excluir | Remover um produto. |

**Essas funcionalidades ainda não estão implementadas.** O projeto contém a configuração inicial e os arquivos organizados para receber o código das próximas etapas.

## 1. O que existe neste momento?

| Item | Situação atual |
| --- | --- |
| `pom.xml` | Configurado com Java 17 e as dependências da atividade. |
| Maven Wrapper | Disponível em `mvnw`, `mvnw.cmd` e `.mvn/wrapper/`. |
| `SpringBootListApplication.java` | Contém o ponto de entrada da aplicação. |
| `application.properties` | Contém as configurações da aplicação e da conexão MySQL. |
| Controller, DTOs, Model, Repository e Service | Arquivos vazios, aguardando implementação. |
| Cinco arquivos HTML em `templates/` | Vazios, aguardando implementação. |
| `static/` | Pasta vazia criada na estrutura inicial. |
| `Dockerfile` | Arquivo vazio; ainda não permite construir uma imagem Docker. |
| `src/test/` | Estrutura reservada para testes, sem testes implementados. |

Ter uma dependência no Maven **não significa que as funcionalidades da aplicação já foram escritas**. O Spring Boot configura a infraestrutura a partir das dependências; o código específico do cadastro será desenvolvido durante a atividade.

## 2. Tecnologias e responsabilidades

| Tecnologia | Para que serve neste projeto |
| --- | --- |
| Java 17 | Linguagem e versão de referência do projeto. Inclui suporte a Records. |
| Spring Boot | Inicializa a aplicação e simplifica a configuração dos componentes. A versão declarada no POM é `3.5.16`. |
| Spring Web MVC | Dá suporte às requisições HTTP, aos Controllers e à integração com as Views. |
| Spring Data JPA | Oferece suporte à persistência por meio de interfaces de repositório. |
| Hibernate | Implementação de JPA que fará o mapeamento entre entidades Java e tabelas. |
| Jakarta Bean Validation | Permite validar dados com anotações como `@NotBlank`, `@NotNull` e `@Size`. |
| Java Records | Recurso da linguagem previsto para os DTOs. Não exige uma dependência Maven própria. |
| Thymeleaf | Processa os templates HTML no servidor. |
| Lombok | Gera código repetitivo, como getters e setters, durante a compilação, quando suas anotações forem usadas. |
| MySQL Server | Servidor responsável por armazenar os dados. Deve estar instalado e em execução separadamente. |
| MySQL Connector/J | Driver que permite à aplicação Java se conectar ao MySQL. Não instala o servidor. |
| Spring Boot DevTools | Oferece recursos de desenvolvimento, como reinicialização após alterações recompiladas. |
| Apache Maven | Gerencia dependências e etapas de compilação e empacotamento. |
| IntelliJ IDEA | IDE usada para editar e executar o projeto. É diferente do Maven. |

**Bootstrap 5 e Bootstrap Icons estão previstos para a interface**, mas ainda não foram incluídos nos templates, pois os HTML estão vazios. Quando usados via CDN, serão referenciados no HTML; não são dependências Java do POM.

## 3. Como ler a árvore de pastas

A indentação indica quem está dentro de quem. Por exemplo, `templates/` fica dentro de `resources/`, e os cinco HTML ficam dentro de `templates/`.

A raiz é a pasta que contém o `pom.xml`. Neste workspace, ela se chama `Modulo07`; o nome Maven do projeto é `springBootList`. Não é necessário criar uma segunda pasta dentro dela.

```text
Modulo07/                         ← raiz do projeto
├── pom.xml                       ← configuração Maven
├── mvnw                          ← Maven Wrapper para Linux/macOS
├── mvnw.cmd                      ← Maven Wrapper para Windows
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── .gitignore
├── .gitattributes
├── README.md
├── Dockerfile                    ← vazio
└── src/
    ├── main/                     ← aplicação
    │   ├── java/                 ← código Java
    │   │   └── com/lab/mvc/springbootlist/
    │   │       ├── SpringBootListApplication.java
    │   │       ├── controller/
    │   │       │   └── ProductController.java
    │   │       ├── dto/
    │   │       │   ├── ProductRequestDTO.java
    │   │       │   └── ProductResponseDTO.java
    │   │       ├── model/
    │   │       │   └── Product.java
    │   │       ├── repository/
    │   │       │   └── ProductRepository.java
    │   │       └── service/
    │   │           └── ProductService.java
    │   └── resources/            ← configurações e recursos
    │       ├── application.properties
    │       ├── static/           ← vazia
    │       └── templates/
    │           ├── alert.html
    │           ├── edit_product.html
    │           ├── index.html
    │           ├── new_product.html
    │           └── validacao.html
    └── test/                     ← reservado para testes
        ├── java/
        └── resources/
```

O caminho `com/lab/mvc/springbootlist/` representa as pastas do pacote Java `com.lab.mvc.springbootlist`. Dependendo da visualização escolhida no IDE, essas pastas podem aparecer agrupadas em uma única linha.

### Por que existem `main` e `test`?

- `src/main/java`: código que faz parte da aplicação.
- `src/main/resources`: arquivos de configuração, templates e outros recursos da aplicação.
- `src/test/java`: código dos testes automatizados, quando forem escritos.
- `src/test/resources`: recursos exclusivos desses testes, quando necessários.

### Por que existem `templates` e `static`?

| Pasta | Uso previsto | Exemplos |
| --- | --- | --- |
| `templates/` | HTML processado pelo Thymeleaf antes de ser enviado ao navegador. | Listagem e formulários de produtos. |
| `static/` | Recursos servidos diretamente ao navegador. | CSS, JavaScript e imagens locais. |

A pasta `static/` veio da estrutura inicial e está vazia. Sua presença não indica que uma interface foi implementada. Ela não é obrigatória quando não há recursos estáticos locais.

## 4. O papel de cada arquivo Java

As responsabilidades abaixo descrevem **o que será implementado**, não funcionalidades já existentes.

| Arquivo | Responsabilidade prevista |
| --- | --- |
| `ProductController.java` | Receber as requisições do navegador, validar entradas, chamar o Service e escolher a resposta ou o template. |
| `ProductRequestDTO.java` | Representar os dados enviados pelo formulário e suas regras de validação. |
| `ProductResponseDTO.java` | Representar os dados que serão apresentados ao usuário. |
| `Product.java` | Representar a entidade de produto e seu mapeamento para o banco. |
| `ProductRepository.java` | Definir o acesso aos dados com Spring Data JPA. |
| `ProductService.java` | Concentrar as regras de negócio, transações e conversões entre entidades e DTOs. |

Há **um arquivo em `service/`**, chamado `ProductService.java`. `ProductRepository.java` pertence a `repository/`. Já `dto/` possui dois arquivos porque entrada e saída de dados têm responsabilidades diferentes.

### O que é MVC?

MVC significa **Model–View–Controller**:

- **Model:** representa os dados do domínio; neste projeto, a entidade prevista é `Product`.
- **View:** apresenta a interface; serão os templates HTML.
- **Controller:** recebe as ações do usuário e coordena as respostas web.

Service, Repository e DTOs complementam essa organização, separando regras de negócio, acesso ao banco e transferência de dados.

### Como será o fluxo de uma operação?

Exemplo conceitual de um futuro cadastro:

```text
Navegador envia o formulário
    ↓
Controller recebe e valida o DTO de entrada
    ↓
Service aplica as regras e prepara a entidade
    ↓
Repository executa a persistência
    ↓
MySQL armazena os dados
```

Se a entrada for inválida, o Controller deverá devolver o formulário com os erros. Na consulta, os dados percorrerão as camadas até chegar à View, usando o DTO de resposta. Esse fluxo ainda precisa ser escrito.

## 5. A classe que inicia a aplicação

O arquivo [SpringBootListApplication.java](src/main/java/com/lab/mvc/springbootlist/SpringBootListApplication.java) contém o código de inicialização:

```java
package com.lab.mvc.springbootlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootListApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootListApplication.class, args);
    }
}
```

Leia esse código por partes:

1. `package` identifica o pacote da classe.
2. Os `import` permitem usar os tipos do Spring Boot.
3. `@SpringBootApplication` marca a classe de configuração principal e habilita a configuração automática e a busca de componentes no pacote e em seus subpacotes.
4. `main` é o ponto de entrada da execução Java.
5. `SpringApplication.run(...)` inicia o contexto Spring e, neste projeto web, o servidor embutido quando a inicialização é bem-sucedida.

Iniciar a aplicação **não cria automaticamente um CRUD**. Os arquivos vazios ainda não definem entidades, rotas ou regras de negócio.

## 6. Entendendo o `pom.xml`

O [pom.xml](pom.xml) é o arquivo que o Maven lê para saber como construir o projeto.

| Configuração | Valor atual | Significado |
| --- | --- | --- |
| `groupId` | `com.lab.mvc` | Identificação do grupo do projeto. |
| `artifactId` | `springBootList` | Nome do artefato gerado pelo Maven. |
| `version` | `0.0.1-SNAPSHOT` | Versão de desenvolvimento do projeto. |
| `java.version` | `17` | Versão Java alvo da compilação. |
| Parent Spring Boot | `3.5.16` | Base que gerencia versões de várias dependências e configurações de build. |

A seção `dependencies` declara as bibliotecas. A seção `build/plugins` configura ferramentas usadas na construção do projeto, incluindo o plugin do Spring Boot e o processamento de anotações do Lombok.

O projeto também possui `spring-boot-starter-test` como dependência de teste. Isso disponibiliza ferramentas, mas **não significa que existam testes escritos**.

### Para que serve o Maven Wrapper?

O Wrapper permite executar a versão de Maven definida pelo projeto sem precisar instalar o Maven globalmente. O JDK continua sendo necessário.

Na primeira execução, ele pode baixar o Maven e as dependências; por isso, é necessário acesso à internet.

| Sistema | Exemplo |
| --- | --- |
| Linux/macOS | `./mvnw --version` |
| Windows PowerShell | `.\mvnw.cmd --version` |

## 7. Entendendo `application.properties`

O arquivo [application.properties](src/main/resources/application.properties) contém:

```properties
spring.application.name=springBootList
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/springbootlist?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo}
spring.datasource.username=${DB_USERNAME:modulo07}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
spring.thymeleaf.encoding=UTF-8
```

| Propriedade | Explicação |
| --- | --- |
| `spring.application.name` | Nome da aplicação. |
| `spring.datasource.url` | Endereço do servidor MySQL e nome do banco. |
| `spring.datasource.username` | Usuário usado para conectar ao MySQL. |
| `spring.datasource.password` | Senha desse usuário, obtida do ambiente. |
| `spring.jpa.hibernate.ddl-auto=update` | Permite ao Hibernate atualizar a estrutura das tabelas a partir das entidades mapeadas, quando elas existirem. |
| `spring.jpa.open-in-view=false` | Desabilita a manutenção de um EntityManager associado à requisição durante a renderização da View; o carregamento dos dados deve ser planejado nas camadas de acesso e negócio. |
| `spring.thymeleaf.encoding=UTF-8` | Define a codificação dos templates, incluindo caracteres acentuados. |

### O que significa `${VARIAVEL:valorPadrao}`?

Essa sintaxe procura uma variável de ambiente. Se ela não estiver definida, usa o valor após os dois-pontos.

| Variável | Se não for informada |
| --- | --- |
| `DB_URL` | Usa a URL MySQL escrita no arquivo. |
| `DB_USERNAME` | Usa `modulo07`. |
| `DB_PASSWORD` | Não possui valor padrão; deve ser definida para a conexão. |

`modulo07` é apenas o nome de usuário padrão configurado. A configuração **não cria esse usuário** no MySQL.

### Como ler a URL padrão?

```text
jdbc:mysql://localhost:3306/springbootlist?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo
```

- `jdbc:mysql`: indica uma conexão JDBC com MySQL.
- `localhost`: o servidor de banco está na mesma máquina em que a aplicação é executada.
- `3306`: porta usada para a conexão.
- `springbootlist`: nome do banco de dados.
- `createDatabaseIfNotExist=true`: solicita a criação do banco se ele não existir, desde que o usuário possua permissão.
- `serverTimezone=America/Sao_Paulo`: informa o fuso usado na configuração da conexão.

O banco precisa estar acessível, e o usuário precisa existir e ter permissões. O driver não instala nem inicia o MySQL Server. Como `Product.java` está vazio, ainda não há um mapeamento JPA de produto para gerar sua tabela.

## 8. Abrindo e conferindo o projeto

### Passo 1 — conferir o Java

No terminal, execute:

```sh
java -version
```

Use JDK 17 para acompanhar a versão indicada pela atividade. A configuração `<java.version>17</java.version>` não instala o JDK e não troca automaticamente o Java do computador.

### Passo 2 — abrir no IntelliJ

1. Abra a pasta que contém o `pom.xml` ou abra o próprio `pom.xml` como projeto Maven.
2. Aguarde a importação das dependências.
3. Configure o SDK do projeto como JDK 17.
4. Configure o Maven Runner para usar JDK 17 e o Maven Wrapper.
5. Confira o processamento de anotações para o uso futuro de Lombok.

Os nomes e a posição das opções podem variar conforme a versão e o idioma do IDE.

### Passo 3 — conferir o Maven

Na pasta do `pom.xml`, execute:

```sh
./mvnw --version
```

Além da versão Maven, a saída informa qual Java o Maven está usando. Confira esse dado caso o terminal e o IDE estejam usando JDKs diferentes.

### Passo 4 — compilar a estrutura

```sh
./mvnw compile
```

Esse comando compila o código Java existente. Ele não inicia o servidor nem comprova conexão com o MySQL. Os arquivos Java completamente vazios não definem classes ou funcionalidades.

## 9. Executando a base da aplicação

Antes de executar, o MySQL Server deve estar em funcionamento, com um usuário válido e acesso ao banco `springbootlist`, ou com permissão para criá-lo pela URL configurada.

No terminal Linux/macOS, configure as credenciais reais:

```sh
export DB_USERNAME='seu_usuario_mysql'
export DB_PASSWORD='sua_senha_mysql'
./mvnw spring-boot:run
```

Os textos `seu_usuario_mysql` e `sua_senha_mysql` são exemplos para substituir. Esses comandos apenas definem valores para a aplicação; **não criam um usuário no MySQL**.

Se o banco estiver em outro endereço, defina também `DB_URL`. As variáveis exportadas valem para esse terminal e os processos iniciados por ele. Um arquivo `.env` não é carregado automaticamente pela configuração atual.

Para executar pelo IntelliJ, adicione as mesmas variáveis na configuração de execução de `SpringBootListApplication`, no campo de variáveis de ambiente. Não presuma que o IDE aberto antes do terminal recebeu os valores exportados nele.

Quando a inicialização for bem-sucedida, o servidor web usará a porta padrão `8080`, salvo configuração externa. **Ainda não existe uma tela de cadastro funcional nem um endpoint `/products` implementado.** Uma página de erro ou ausência de conteúdo no navegador não significa, por si só, que a estrutura de pastas está errada.

Para encerrar a execução no terminal, pressione `Ctrl+C`.

## 10. Comandos Maven: o que cada um faz

Execute estes comandos na raiz do projeto:

| Comando | O que faz |
| --- | --- |
| `./mvnw --version` | Exibe as versões de Maven e Java usadas. |
| `./mvnw compile` | Compila o código principal. |
| `./mvnw test` | Executa os testes existentes; atualmente não há testes implementados. |
| `./mvnw package` | Executa as etapas de build e gera o JAR. |
| `./mvnw clean` | Remove os arquivos gerados na pasta `target/`. |
| `./mvnw spring-boot:run` | Tenta iniciar a aplicação, incluindo a configuração de banco. |

No Windows, substitua `./mvnw` por `.\mvnw.cmd` no PowerShell.

Após um empacotamento bem-sucedido, o artefato esperado é:

```text
target/springBootList-0.0.1-SNAPSHOT.jar
```

A pasta `target/` é saída de build. Ela não é o local para escrever o código-fonte e pode ser recriada pelo Maven.

## 11. Dúvidas e problemas comuns

| Situação | O que conferir |
| --- | --- |
| Os arquivos de produto estão vazios. | É o estado esperado: somente a estrutura foi preparada. |
| Existem dois DTOs. | Um foi reservado para entrada de dados e outro para saída. |
| O IDE mostra pacotes agrupados. | Confira a visualização de pacotes; o agrupamento não significa duplicação de arquivos. |
| Existe uma pasta `static/`. | É uma pasta padrão de recursos estáticos, atualmente vazia. |
| O HTML não aparece como uma tela funcional. | Os templates estão vazios e o Controller ainda não foi implementado. |
| A conexão retorna `Access denied`. | Confira usuário, senha e permissões no MySQL. |
| A conexão retorna `Connection refused` ou falha de comunicação. | Confira se o MySQL está iniciado e se host e porta estão corretos. |
| O banco não existe e não foi criado automaticamente. | Confira as permissões de criação do usuário ou prepare o banco manualmente. |
| Há erro relacionado a `DB_PASSWORD`. | Defina a variável no ambiente usado para iniciar a aplicação. |
| A porta `8080` está ocupada. | Confira se outra execução da aplicação já está aberta. |
| `./mvnw` retorna `Permission denied` no Linux. | Confira a permissão de execução do arquivo; `chmod +x mvnw` a habilita. |
| O download de dependências falha. | Confira internet, proxy e acesso ao repositório Maven. |
| O Dockerfile não funciona. | Ele está vazio e foi mantido apenas como parte da estrutura solicitada. |

## 12. O que ficará para as próximas etapas

Conforme as instruções da atividade forem sendo fornecidas, os arquivos poderão receber:

1. O mapeamento da entidade `Product`.
2. Os Records de entrada e saída e suas validações.
3. A interface de persistência.
4. As regras e transações do Service.
5. As rotas e o tratamento dos formulários no Controller.
6. As telas Thymeleaf e os recursos visuais.
7. Os testes e, se solicitado, a configuração Docker.

Esta lista descreve o trabalho futuro. Nenhuma dessas implementações faz parte do estado atual da estrutura.
