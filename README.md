# Prepare API Testing Instance or Environment

### Prerequisite software
* [Download & Install JDK](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [Download & Install IntelliJ IDEA Community Version](https://www.jetbrains.com/idea/download/)

### Create Java(Gradle) Project in IntelliJ IDEA for API
* Create Java(Gradle) Project in IntelliJ IDEA
* Add Gradle Dependencies
    - [REST Assured](https://mvnrepository.com/artifact/io.rest-assured/rest-assured)
    - [Json Path](https://mvnrepository.com/artifact/io.rest-assured/json-path)
    - [TestNG](https://mvnrepository.com/artifact/org.testng/testng)
    - [lorem ipsum](https://mvnrepository.com/artifact/com.thedeanda/lorem)
    - [Json Simple](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple)
    - [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
    - [ExtentReports](https://mvnrepository.com/artifact/com.aventstack/extentreports)
* **Create TestNG XML** plugin install from Marketplace - File >> Settings >> plugin >> Marketplace >> search 'Create
  TestNG XML' & install

### Related Resource
  * [Getting Started](https://github.com/rest-assured/rest-assured/wiki/GettingStarted)
  * [Usage Guide](https://github.com/rest-assured/rest-assured/wiki/Usage)
  * [Dummy API URL](https://reqres.in/)
  * [JSON Path Finder](https://jsonpathfinder.com/)
  * [JSONPath Online Evaluator](https://jsonpath.com/)
  * [Extent Reports](https://www.extentreports.com/docs/versions/5/java/index.html)
  * [TestNG](https://www.javatpoint.com/testng-tutorial)

### Run Test Case
  * **Run Test Case** - Go to desired Java Class where has Test Case, Right click of mouse on Test Case >> click on Run
  * **Run XML File** - After create TestNG file, Right click of mouse on TestNG xml >> click on Run
