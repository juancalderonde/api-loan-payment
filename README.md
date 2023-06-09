# General Description
This repository creates an api with spring boot for loan payment calculation, for example allows to create a table with all the future payments given an interest rate and a payment frequency

# Requirements
  - Java jdk 1.8 or above -> https://www.oracle.com/java/technologies/downloads/#java17
  - Maven 3.9.0 or above -> https://maven.apache.org/download.cgi
  - IDE such as spring tools suite or intellij
# How to build

This api is developed with Maven, and has an embedded tomcat server. for that reason is only necesary to build as a jar o war package for running in a docker image or into an apache server

1. Clone this repository

  using command line
  ```bash
  $ git clone https://github.com/juancalderonde/api-loan-payment.git
  ```
  using IDE
  
  Depending on the IDE used is possible to clone this repository directly from the interface, in particular both intellij and spring tools suite has this option
  
2. Using maven make the install of the solution the pom.xml has by default a war package option, but it can be modified


# Deploy

Using a docker image for deploy

  ```bash
  FROM openjdk:11-jdk-slim
  COPY /loan-payment-0.0.1-SNAPSHOT.war /loan-payment-0.0.1-SNAPSHOT.war
  EXPOSE 8080
  CMD java -jar loan-payment-0.0.1-SNAPSHOT.war
  ```

# Demo
If you want to see a ddeployment of the api -> https://www.juancalderondev.com/loanpayment