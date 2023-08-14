# Диплом "Тестировщик ПО"
## Название проекта
Проект тестирует сервис покупки тура дебетовой картой или с помощью кредита.

## Необходимое ПО
* github;
* IntelliJ IDEA;
* Java11;
* Docker;
* Browser;
## Инструкция по запуску
1. Склонируйте репозиторий;
1. Запустите контейнер в терминале InteliJ Idea, в котором разворачивается база данных  `docker-compose up -d --force-recreate`  
1. Запустите SUT командой :
    * для использования Postgres: `java -Dspring.datasource.url=jdbc:postgresql://185.119.57.64:5432/postgres -jar aqa-shop.jar` 
    * для использования MySQL: `java -Dspring.datasource.url=jdbc:mysql://185.119.57.64:3306/app -jar aqa-shop.jar` 
1. Запустите тесты командой: 
    * при работе с postgres: `gradlew clean test -Ddb.url=jdbc:postgresql://185.119.57.64:5432/postgres` 
    * при работе с mySql: `gradlew clean test -Ddb.url=jdbc:mysql://185.119.57.64:3306/app`
