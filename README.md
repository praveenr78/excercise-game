
# FizzBuzz Game Excercise

This is a Java based Spring Boot Application to generate and output FizzBuzz game.


## Setup to run locally

### Pre-requisites

1. JRE 1.8.0.1
2. Docker ( Docker for Desktop to run locally)
3. Gradle



### Configuration

 You will need to build the artifacts for the source code, using `gradle`.

```sh
./gradlew clean build
```
The apps should then startup cleanly if you run

```sh
docker-compose up --build
```

Application will start at http://localhost:8080 

```sh

Default
http://localhost:8080/game/fizzbuzz/generate

With Limit
http://localhost:8080/game/fizzbuzz/generate?maxValue=15
```

Swagger docs at http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs&validatorUrl=#/