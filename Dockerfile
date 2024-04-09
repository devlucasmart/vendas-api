# Use a imagem base do Maven para compilar o projeto
FROM maven:3.8.5-openjdk-17 AS build

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Baixar as dependências do Maven (apenas o pom.xml é copiado, portanto as dependências são cacheadas se o pom não mudar)
RUN mvn dependency:go-offline

# Copiar todo o código-fonte para o diretório de trabalho
COPY src ./src

# Compilar o projeto
RUN mvn package -DskipTests

# Use a imagem base do OpenJDK 17 para executar a aplicação
FROM adoptopenjdk/openjdk17:jdk-17.0.0_35-alpine AS runtime

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar qualquer arquivo .jar encontrado dentro do diretório target para o diretório de trabalho
COPY --from=build /app/target/*.jar /app/

# Expor a porta em que a aplicação Spring Boot estará em execução (ajuste conforme necessário)
EXPOSE 8080

# Comando a ser executado ao iniciar o contêiner
CMD ["java", "-jar", "*.jar"]
