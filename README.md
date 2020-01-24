# keeper-ui-tests

Project containing the **automated tests** for **KEEPER** on **UI level**.

## Frameworks & Patterns

- **Selenium** - Automate test steps in the browser
- **Page Object Pattern** - Define each web page under test as page object
- **Cucumber** - Tests are defined as scenarios in the feature files
- **Spring** - Dependency injection used for WebDriver and page objects 
- **TestNG** - Run the cucumber scenarios as tests

## Setup and Execution

1. Clone the repository on your machine.
2. Configure basic test data (see testData.properties), consistent with your test environment, in the Maven settings.xml.
3. Run tests by using Maven goal "clean test".
