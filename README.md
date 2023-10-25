# Parking Meter :car: :bar_chart:
**Tech Challenge - Phase 3 - Graduate/Pós-Graduação**

GitHub Repository: https://github.com/igorgrv/parkingmeter-graduate
Swagger: http://localhost:8080/swagger-ui/index.html

## About :book:

Welcome to Parking Meter Monitor! An innovative project that combines the powerful technologies of:

* Java 17;
* MongoDB;
* Maven; 
* Spring Boot;
* Spring Validation;
* Lombok;

## Working with MongoDB

To get started you need to:
* Install MongoDB Community Server: https://www.mongodb.com/try/download/community
* After that, execute in a terminal:  **`mongosh`**
* With the server up and running, execute the following commands:

```bash
use parking-meter
```


### Basic MongoDB Commands

|           **MongoDB Command**            | **Description**                                     |
| :--------------------------------------: | :-------------------------------------------------- |
|      **`mongod`** or **`mongosh`**       | Starts mongoDB Server                               |
|           **`show databases`**           | Show all the databases                              |
|          **`use databaseName`**          | Select the Database                                 |
|          **`show collections`**          | Show all the collections for the specific database  |
|       **`db.collectName.drop()`**        | Drop the specified collection                       |
| **`db.nomeCollection.countDocuments()`** | Count the documents given a collection              |
|      **`db.nomeCollection.find()`**      | Shows all the documents of the specified collection |



## Entity-relationship

<img src="./Documents/Entities.png" alt="Entities" style="zoom: 100%;" />

## Challenges

* Finding best practices for a nonSql project
* Work with NoSQL databases
* Entity-relationship modeling


1 - Criar repositório para subir imagens docker no ECR
2 - Subir o arquivo buildspec.yml para buildar o projeto usando o CodeBuild
