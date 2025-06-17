# Shop System

Проект представляет собой микросервисную архитектуру для обработки заказов и платежей, включая API-шлюз, сервис заказов и сервис платежей.

## Функциональные возможности

- Создание заказов пользователями.
- Автоматическое снятие средств с баланса пользователя.
- Асинхронная обработка событий через Kafka.
- Обновление статуса заказа в зависимости от результата платежа (успешно или отменён).

## Стек технологий

- Java 17
- Spring Boot 3.2
- Spring Cloud Gateway
- Spring Data JPA
- PostgreSQL
- Kafka
- Docker / Docker Compose
- Swagger / Springdoc OpenAPI
- Maven

## Архитектура

| Сервис         | Назначение |
|----------------|------------|
| **API Gateway**       | Получает все HTTP-запросы и маршрутизирует их к нужным микросервисам  |
| **Orders Service**    | Управляет заказами, сохраняет их в БД, публикует события о создании заказов  |
| **Payments Service**  | Получает события заказов, списывает средства, отправляет результат обратно  |
| **PostgreSQL**        | Хранит данные заказов и платежей  |
| **Kafka**             | Служит шиной событий между микросервисами  |

Микросервисы взаимодействуют между собой через Kafka и REST-запросы.

## Инструкция по запуску

### Требования:

- Docker
- Docker Compose
- Java 17
- Maven 3.8+

### Сборка всех сервисов:

```bash
cd orders-service
mvn clean package -DskipTests

cd ../payments-service
mvn clean package -DskipTests

cd ../apigateway
mvn clean package -DskipTests
```

### Запуск через Docker Compose:

```bash
docker-compose up --build
```

### Swagger-документация

Swagger доступен по адресу:

```
http://localhost:8080/swagger-ui.html
```


