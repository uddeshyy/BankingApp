# BankingApp


[![GitHub issues](https://img.shields.io/github/issues/uddeshyy/BankingApp)](https://github.com/uddeshyy/BankingApp/issues)
[![forks](https://img.shields.io/github/forks/uddeshyy/BankingApp)](https://github.com/uddeshyy/BankingApp/network)
[![stars](https://img.shields.io/github/stars/uddeshyy/BankingApp)](https://github.com/uddeshyy/BankingApp/stargazers)

This is basic Banking Service Process Flow designed using Camunda BPMN integrated with Spring boot. 

Using MySQL RDBMS and JDBC as Connector, this design is fast, reliable and scalable.
Enjoy !!!

## What you need

* Java openJDK 11 or above
* MySQL 5.6 or better
* Camunda Modeller 5.0.0
* Camunda Platform 7 (Strictly)
* Any suitable IDE (I used Eclipse)
* Port 3306(for sql) and 8080(for rest-engine)


## Installation
1. Clone this repo - ``` git clone https://github.com/uddeshyy/BankingApp ```
2. Switch to mysql server cli and run [TableScript.sql](https://github.com/uddeshyy/BankingApp/blob/master/src/main/resources/TableScript.sql)
3. Run [Application.java](https://github.com/uddeshyy/BankingApp/blob/master/src/main/java/com/banking/workflow/Application.java) and wait for engine to start
4. Proceed when `JobExecuter` has started
5. Open Camunda Modeller and open [main_process.bpmn](https://github.com/uddeshyy/BankingApp/blob/master/src/main/resources/main_process.bpmn)
6. Deploy the process to rest endpoint with additional files (forms) in [Forms Folder](https://github.com/uddeshyy/BankingApp/tree/master/src/main/resources/static/forms)
7. Open Tasklist and Start process

## Main Process
The workflow consist of 3 Major services, all connected to each other.

![alt text](https://user-images.githubusercontent.com/53940727/176218095-1eadd8a6-f7a5-4a7d-8447-8d0b63a693bf.JPG)

All inputs and output tasks are marked as User task

Used all 3 types of Forms



Errors are catched using boundary events and message diplayed in the task form itself

![login_form](https://user-images.githubusercontent.com/53940727/176220989-75553824-811b-4686-bc26-728117268acc.JPG)

## Contribution, Issues, and Updates

* Fork It !!!!!
* Any kind of contribution will be accepted.
* Kindly raise appropriate issues.
* New to Github so you might teach me some Tips and Features.
