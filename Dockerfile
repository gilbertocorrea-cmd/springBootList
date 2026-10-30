# Estágio 1: Build da Aplicação com Maven e OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# Baixa dependências para cache
RUN mvn dependency:go-offline -B
COPY src ./src
# Compila e gera o arquivo JAR executável ignorando os testes unitários de ambiente
RUN mvn clean package -DskipTests
# Estágio 2: Imagem de Execução Leve com JRE 17
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Crio um usuário sem privilégios administrativos para executar a aplicação.
RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=build --chown=spring:spring /app/target/*.jar app.jar
USER spring:spring
# Configuração da Porta dinâmica da Nuvem (Render/Koyeb definem a variável PORT)
ENV PORT=8080
EXPOSE 8080
# Inicialização da aplicação Spring Boot
ENTRYPOINT ["sh", "-c", "exec java -jar app.jar --server.port=${PORT}"]
