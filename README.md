### farmdrop-web-automation


### Installation (pre-requisites)
1. JDK 1.8+ -iDE
2. Maven -Build Tool
3. Eclipse -latest
4. Eclipse Plugins for
    - Maven
    - Cucumber
5. Browser driver -latest

### Framework set up
Donwload from :

### Run Some Sample Tests
Open terminal (MAC OSX) or command prompt / power shell (for windows OS) and navigate to the project directory
type `mvn clean test` command to run features. With this command it will invoke the default Firefox browser and will execute the tests.

##Mac OSX:
##Software preparatation

            
      $ mkdir aut-tests
          git clone https://github.com/jembi/auto-test-cucumber-framework.git
          mvn clean test

- To run features on specific browser use, `mvn test "-Dbrowser=browser_name"`
browser_name can be one of following but make sure that browser’s driver file are present and specified in system variable. -- ff -- chrome .

Please note that browser drivers are not included as part of this framework. The reason for not including is that selenium browser driver version are varies based on the browser version that you are using and also selenium server version.

- To run specific feature if you have multiple feature files use,
`mvn test -Dcucumber.options="classpath:features/********(filename).feature"`


### Develop automation scripts using BDD approach with Cucumber Java language
There are already many predefined StepDefinitions which is packaged under `/stepdefinitions/PredefinedStepDefinitions.java` will help you speed up your automation development that support both your favorite workaday helpers methods.

Tests are written in the Cucumber framework using the Gherkin Syntax. More about Gherkin & Cucumber can be found at https://cucumber.io/docs/reference A typical test will look similar to this:

```
Feature: New User Registration 

    As a new customer to farmdrop 
    I need to register to my account
    And do the neccasry grocery shopping

    Background:

        Given User should be in the User Registration page

    Scenario: New customer registration or account creation
        When  User Click on SignUp Link 
        Then  User Navigate to resistration form page
        Then  Usershould see Registration form

    Scenario Outline: User registration with passing test data as data table
        Then User should enter "<Email address>"
        Then User should enter "<password>"
        Then User shoiuld enter "postcode>"
        And User should select the radio buttons 
        When User click on Lets fix the food chain button
        Then USer should see the navigate dashboard page
        Then User should see the products results in account dashboard page


        Examples:
        |Email address|         |passowrd|  |postcode|
        |frodroptest@gmail.com| |*******|   |E1 7AE|
```

### The Page Object Design Pattern(POM)
Within your web app's UI there are areas that your tests interact with. A Page Object simply models these as objects within the test code. This reduces the amount of duplicated code and means that if the UI changes, the fix need only be applied in one place. In other wards one of the challenges of writing test automation is keeping your [selectors] (classes, id's, or xpath' etc.) up to date with the latest version of your code. The next challenge is to keep the code you write nice and `DRY (Don't Repeat Yourself)`. The page object pattern helps us accomplish this in one solution. Instead of including our selectors in our step definitions(in cucumber) we instead place them in a <pagename>.java file where we can manage all these selectors and methods together. Your test file should only call the test methods.

You can also place reusable methods or logic inside of these pages and call them from your step java files. The page object serves as a layer of abstraction between tests and code. When A test fails, it fails on a individual step. That step may call a selector that is no longer valid, but that selector may be used by many other steps. By having a single source of truth of what the selector is supposed to be, fixing one selector on the page object could repair a number of failing tests that were affected by the same selector.

#Cucumber Selenium - 
Overall testframework leveraging the Cucumber framework with Selenium written in JAVA.

#Feature File - 
The feature file specifies the steps in BDD language style

## Hooks class - 
Hooks class is most important class as it performs the following functions
Ensure the correct chromedriver depending on environment specified through Maven commands

## Step Definition Feature File - 
Java class whereby the steps from  the feature file are broken down to be coded into automation tests

### Reporters
Once you ran your tests you can generate the various types of reports. This framework `farmdrop-web-automation` uses several different types of test reporters to communicate pass/failure.
##### Allure Report:
You can generate a report using one of the following command.
- `mvn allure:serve`

Report will be generated into temp folder. Web server with results will start appearing in your default browser. This is very handy if you are running test in CI/CD environment and wants to access the execution report. Note: If your default browser is IE, Allure repots does not appear due to the style sheet compatibility issue. To get rid of this problem you can use any other browser as a default browser or copy the Allure report url from the IE browser and use it in any other browser. 

- `mvn allure:report`

Report will be generated tо directory: `target/site/allure-maven/index.html` and you can view it locally.

##### HTML Report:
To generate HTML report use  `mvn test -Dcucumber.options="–plugin html:target/result.html"`

##### JSON Report:
To generate a JSON report Use `mvn test -Dcucumber.options="–plugin json:target/result.json"`



