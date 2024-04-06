# Estágio 1: Construir a aplicação Spring com Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Copia o código-fonte para o diretório de trabalho
COPY . /usr/src/app

# Define o diretório de trabalho
WORKDIR /usr/src/app

# Empacota a aplicação utilizando o Maven
RUN mvn clean package -DskipTests

# Estágio 2: Executar a aplicação Spring em um contêiner
FROM adoptopenjdk/openjdk17:alpine-jre

# Define o diretório de trabalho
WORKDIR /usr/src/app

# Copia o arquivo JAR construído no Estágio 1 para o diretório de trabalho no Estágio 2
COPY --from=build /usr/src/app/target/*.jar app.jar

# Define o comando para iniciar a aplicação Spring automaticamente ao iniciar o contêiner
ENTRYPOINT ["java", "-jar", "app.jar"]
