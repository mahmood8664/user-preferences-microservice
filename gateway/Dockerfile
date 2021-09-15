FROM openjdk:11
COPY ./build/libs/*.jar ./app.jar
EXPOSE 8080
CMD ["sh", "-c", "java -jar ./app.jar"]
