FROM openjdk:17-alpine

RUN addgroup -S quiz && adduser -S quiz -G quiz

USER quiz:quiz

COPY ./build/libs/quiz-0.0.*.jar app/quiz.jar

CMD ["java","-jar", "app/quiz.jar"]
