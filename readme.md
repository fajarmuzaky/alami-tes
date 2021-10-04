# alami test

# Tech Stack

I used these tech stack for create this project with:
* Java 8
* [Spring Boot](https://spring.io/projects/spring-boot)
* PostgreDB (as main data storage)
* MongoDB (as log data storage)
* apache maven

# Implemented Features

This tables shows which features that has been implemented by this repository.

:white_check_mark: : ready

:heavy_exclamation_mark: : in progress

:x: : not yet implemented

| Features                        | Status                              |
| ------------------------------- | ----------------------------------- |
| Get All Members                 | :white_check_mark:                  |
| Add New Member                  | :white_check_mark:                  |
| Get All Transaction             | :white_check_mark:                  |
| Create Transaction              | :white_check_mark:                  |
| Get All Transaction Log         | :white_check_mark:                  |
| Create Transaction Log By ID Member         | :white_check_mark:      |
| Create Log using Kafka             | :heavy_exclamation_mark:            |
| Unit Test                       | :heavy_exclamation_mark:            |
| Api Documentation with Swagger  | :x:                                 |

# How to Run this project

- Clone this project
- Open this project with your IDE
- Create database on postgre "alami_transaction"
- Create database on Mongodb "alami_transaction"
- Run and build this project
- import the collection postman for test endpoint
    

# Type for create transaction

| Code  | Type             |
| ----- | -----------------|
| 1     | DEPOSIT          |
| 2     | LOAN             |
| 3     | WITHDRAW         |
| 4     | REPAYMENT        |
   