# ASQF TA Challange - Team ZDI HUngary

This repository contains an end-to-end test example to verify the automated Web Testing service of webmate.
The test executes a simple selenium test suite through webmate remote webdriver as a precondition.
After the successful execution of the precondition the test opens the Webmate GUI and verifies that the corresponding test run is displayed within the TestLab screen. The test opens the same test run and verifies if all the actions are displayed in the screen.

## Pre-requisites
1. Install Java JDK 17
2. Set environment variable `JAVA_HOME=<jdk-install-path>`
3. Install Maven 3.6.3
4. Specify the webmate remote webdriver url, and required OS/Browser versions in the [driver.properties](/src/test/resources/driver.properties) file
5. Specify the settings for the webmate test run, like api key, project id, etc in [webmate.properties](./src/test/resources/webmate.properties) file
6. Specify the settings for the webmate user credentials in [user.properties](./src/test/resources/user.properties) file
## Execute tests
Run the tests with: `mvn clean test`
