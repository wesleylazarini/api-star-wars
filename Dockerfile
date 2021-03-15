FROM openjdk:11
ADD ./target/api-star-wars-1.0.jar /usr/src/api-star-wars.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "api-star-wars.jar"]