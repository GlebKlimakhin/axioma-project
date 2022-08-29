FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /application/axioma/src
COPY src  ./
COPY pom.xml  ../
RUN mvn -f /application/axioma/pom.xml clean package -DskipTests
##STAGE 2
FROM openjdk:17-oracle
WORKDIR /application/lib/
COPY --from=builder /application/axioma/target/axioma-trainee.jar /sb-dockerized.jar
EXPOSE 3100
ENTRYPOINT ["java", "-jar", "/application/lib/sb-dockerized.jar"]

