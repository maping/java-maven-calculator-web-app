# java-maven-calculator-web-app
Java Calculator Web App, Build by Maven, Including JUnit Test, Integration Test，Performance Test, CI/CD by Jenkins etc.

![image](https://github.com/maping/java-maven-calculator-web-app/raw/master/realworld-pipeline-flow.png)

JUnit test: mvn clean test

Integration test：mvn clean integration-test

Deploy to an existed Tomcat 8x: mvn cargo:run (you need change pom.xml, point to your Tomcat 8x)

Performance test：mvn clean verify

Build project site: mvn site
