FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} TelegramBotWithChatGPT-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/TelegramBotWithChatGPT-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080