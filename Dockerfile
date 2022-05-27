FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=bla_bla
ENV BOT_TOKEN=bla_bla
ENV BOT_DB_USERNAME=default_jrtb_db_user
ENV BOT_DB_PASSWORD=default_jrtb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar","/app.jar"]
