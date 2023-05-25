[![Codacy Badge](https://app.codacy.com/project/badge/Grade/259dc26350b54b638f5c38b1386190ce)](https://app.codacy.com/gh/VladimirKokourov/vote_restaurants/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
## Restaurant voting system

Design and implement a REST API using Hibernate/Spring/SpringMVC without frontend.

A voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a food name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user vote again the same day:
- If it is before 11:00 we assume that he changed his mind.
- If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides a new menu each day.

## Stack

- JDK 17
- Spring Boot 3.0.6
- Lombok
- H2
- Swagger

## Running

- Run```mvn spring-boot:run``` in project root directory.
- Open [localhost](http://localhost:8080/)

## Testing credentials.

| email | password |
| ------ | ------ |
| user@gmail.com | password |
| admin@gmail.com | admin |
| guest@gmail.com | guest |

## API documentation

- [swagger](http://localhost:8080/swagger-ui/index.html) - Swagger ui