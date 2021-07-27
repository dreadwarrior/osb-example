# Open Service Broker API example

## Requirements

  * Java 16

## Setup

  * adjust `osb-example.user-name` and `osb-example.password` in 
    `application.properties` to your needs
  * `./mvnw spring-boot:run`

## Test

  * use `evoila/osb-checker-kotlin` to run integration tests
    * tested on Java 11
    * adjust `build.gradle`: `kotlinOptions.jvmTarget = "11"`
    * adjust `RestAssureConfig` to allow OSB API version `2.16`
  * put the following content into an `application.yml` in project root:

        config:
          url: http://localhost
          port: 8080
          apiVersion: 2.16
          user: osb
          password: example

  * run the tests `./gradlew bootRun`