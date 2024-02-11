# DTC Global Internship Test Task Solutions

This repository contains solutions for two tasks as part of the DTC Global internship test.

## Task 1: Currency Converter API

The Currency Converter API is a Spring Boot application that provides endpoints for currency conversion and retrieving exchange rates.

### Endpoints

#### 1. Convert Currency

- **GET** `/api/convert`

  **Request Parameters:**
    - `currencyFrom` (String): The currency code to convert from (e.g., USD, EUR).
    - `currencyTo` (String): The currency code to convert to (e.g., GBP, JPY).
    - `amount` (Integer): The amount to convert.

#### 2. Get Exchange Rate

- **GET** `/getRate/{currencyFrom}/{currencyTo}`

  **Path Variables:**
    - `currencyFrom` (String): The currency code of the initial currency.
    - `currencyTo` (String): The currency code of the target currency.

### Data Source

The API uses [Many APIs](https://manyapis.com) for parsing currency rates.

### How to Run

First of all, create .env file and write apikey for manyapis service

```
CURRENCY_SERVICE_APIKEY="<your_api_key>"
```

To run the Currency Converter API, execute the following command in the project directory:

```sh
./mvnw spring-boot:run
```

## Task 2: Dynamic JAR Loader

This task is focused on creating a program that can dynamically load classes and methods from a JAR file during runtime, based on user inputs. This solution allows for the flexible execution of methods within any given JAR without hardcoding the class or method names.

### Objective

Develop a Java application that:

- Accepts a path to a JAR file and user inputs for a class name and method name to be invoked.
- Dynamically loads the specified class from the JAR file.
- Prompts the user to input the number of parameters, their types, and values for the specified method.
- Invokes the method with the user-provided arguments and outputs the result.

### Features

- **Dynamic Class Loading**: Load any class from a specified JAR file at runtime.
- **Flexible Method Invocation**: Dynamically invoke any method with parameters as specified by the user.
- **User Interaction**: Interactive prompts for user inputs regarding the JAR file, class, method, and parameters.

### How to Run

To run the Dynamic JAR Loader program, follow these steps:

1. Compile the Java program that implements the dynamic loading feature. Ensure your Java file is named appropriately, for example, `Main.java`.

2. Execute the compiled program using the Java command. Make sure you have the path to the compiled class files or the JAR file containing the program. The command will look something like this:

    ```sh
    java -cp Main.java Main
    ```
   
3. Follow the on-screen prompts provided by the program to specify the JAR file path, class name, method name, number of parameters, and their types and values.

### Example

Assuming you have a JAR file `stringMultiplier.jar` that contains a class `StringMultiplier` with a method `multiply(String input, Integer multi)`, you would:

- Provide the path to `stringMultiplier.jar` when prompted.
- Enter `StringMultiplier` as the class name.
- Specify `multiply` as the method to invoke.
- Indicate the number of parameters (2 in this case) and their types (`String` and `Integer`) along with their values when prompted.

The program will then dynamically load `StringMultiplier` from `stringMultiplier.jar`, invoke `multiply` with the provided parameters, and display the result.

### How to Create a JAR File

For packaging your Java classes into a JAR, use the following commands:

1. Compile your Java code:

    ```sh
    javac StringMultiplier.java
    ```

2. Package the compiled classes into a JAR file:

    ```sh
    jar cvf stringMultiplier.jar *
    ```

Replace `StringMultiplier.java` with the name of your Java file and `stringMultiplier.jar` with the desired name for your JAR file.

This task demonstrates advanced Java capabilities, including reflection, dynamic class loading, and interaction with user inputs to execute arbitrary code contained within a JAR file.
