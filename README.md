# Telegram bot with chatGPT integration

Simple telegram bot with chatGPT integration.

Admin panel:

`GET: /admin `

# Technologies used:
- Java 17
- Spring Boot, Spring Data, Spring Security
- PostgreSQL

# How to run this project on your computer:
1. Clone this project:
```bash
git clone https://github.com/NikitaKvl/telegram-bot-with-chatGPT.git
```
2. Add [Lombok](https://projectlombok.org/setup/overview) plugin to your IDE
3. Install [Docker](https://www.docker.com/products/docker-desktop/) on your local machine
4. Configure `docker-compose.yml` :
```java
    - POSTGRES_USER=YOUR_DB_USERNAME
    - POSTGRES_PASSWORD=YOUR_DB_PASSWORD
    - POSTGRES_DB=YOUR_DB_NAME
    
    - SPRING_DATASOURCE_USERNAME=YOUR_DB_USERNAME
    - SPRING_DATASOURCE_PASSWORD=YOUR_DB_PASSWORD
    - TELEGRAM_BOT_NAME=YOUR_BOT_NAME
    - TELEGRAM_BOT_TOKEN=YOUR_BOT_TOKEN
    - CHAT_API_KEY=YOUR_CHAT_API_KEY
```
6. Run docker-compose file
```bash
docker-compose up
```

After all these steps you will be able to run this project locally.