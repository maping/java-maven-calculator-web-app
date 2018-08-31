# A Java Maven Calculator Web App
A Java calculator web app, build by Maven, CI/CD by Jenkins.

![image](https://github.com/maping/java-maven-calculator-web-app/raw/master/realworld-pipeline-flow.png)

## 1. Build
```shell
mvn clean package
```

### 2. Run Locally
```shell
mvn jetty:run
```
By default, the jetty port is 9999, so you should visit following urls in browser:

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
## Containerize Your Web App
1. Build a docker image using `Dockerfile`:
   ```
   docker build -t calculator .
   ```
2. Run docker image locally
   ```
   docker run --rm -p 8080:8080 calculator
   ```
3. Then you can access the web app at http://localhost:8080 in browser
## Deploy Your Web App to An Existed Tomcat 8x
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
