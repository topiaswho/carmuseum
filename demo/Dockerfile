#
# Mvn Build Stage
#
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn clean package

#
# Jar Package Stage
#
FROM eclipse-temurin:17-jre-focal
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/carmuseum.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/carmuseum.jar"]
