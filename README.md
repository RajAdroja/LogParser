CMPE 202 Individual Project 
# Project Name

## Overview
This project processes an input text file and generates three JSON files: `apm.json`, `application.json`, and `request.json`.

## Prerequisites
- Java 8 or higher
- Maven 3.x or higher

## Setup



To generate the JSON files from a given input file, run the following command:

    mvn exec:java -Dexec.args="--file src/main/resources/input.txt"


Generated Files:  
apm.json: Contains APM-related data.  
application.json: Contains application-related data.  
request.json: Contains request-related data.


To run Junit test, run the following command:

        mvn test
