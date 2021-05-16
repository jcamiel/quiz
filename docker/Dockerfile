FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine

RUN addgroup -S quiz && adduser -S quiz -G quiz

USER quiz:quiz

COPY ./build/libs/quiz-0.0.1-SNAPSHOT.jar app/quiz.jar

CMD ["java","-jar", "app/quiz.jar"]
