# A Java Maven Calculator Web App
A Java calculator web app, build by Maven, CI/CD by Jenkins.

![image](https://github.com/maping/java-maven-calculator-web-app/raw/master/realworld-pipeline-flow.png)

## Build
```shell
mvn clean package
```

## Run Locally
```shell
mvn jetty:run
```
By default, the jetty port is 9999, so you should visit

http://localhost:9999/calculator/api/calculator/ping

http://localhost:9999/calculator/api/calculator/add?x=8&y=26

http://localhost:9999/calculator/api/calculator/sub?x=12&y=8

http://localhost:9999/calculator/api/calculator/mul?x=11&y=8

http://localhost:9999/calculator/api/calculator/div?x=12&y=12

To run in a different port
```shell
mvn jetty:run -Djetty.port=<your port>
```
## Debug Locally
```shell
set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n
mvn jetty:run
```
## Run JUnit Test
```shell
mvn clean test
```
## Run Integration Test
```shell
mvn clean integration-test
```
## Deploy web app to an existed Tomcat 8x
You need change pom.xml, point to your Tomcat 8x.
```shell
mvn cargo:run
```
## Run Performance Test with JMeter
You need install Jmeter first, and make sure your Tomcat 8x is runing.
```shell
mvn clean verify
```
## Build Project Site
```shell
mvn site
```
