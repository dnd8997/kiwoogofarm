FROM bellsoft/liberica-openjdk-alpine:17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
# kiwoogofarm-0.0.1-SNAPSHOT.jar 파일을 app.jar로 복사
COPY --from=builder /app/build/libs/kiwoogofarm-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
