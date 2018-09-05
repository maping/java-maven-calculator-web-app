# A Java Maven Calculator Web App
A Java calculator web app, build by Maven, CI/CD by Jenkins.

![image](https://github.com/maping/java-maven-calculator-web-app/raw/master/realworld-pipeline-flow.png)

image from https://jenkins.io/doc/book/pipeline/

## 1. Manualy Build, Test, and Deploy By Maven

### 1.1 Build
```shell
mvn clean package
```

### 1.2  Run Locally
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
### 1.3 Debug Locally
```shell
set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n
mvn jetty:run
```
### 1.4 Run JUnit Test
```shell
mvn clean test
```
### 1.5 Run Integration Test
```shell
mvn clean integration-test
```
### 1.6 Deploy Your Web App to An Existed Tomcat 8x
You need change pom.xml, point to your Tomcat 8x.
```shell
mvn cargo:run
```
### 1.7 Run Performance Test with JMeter
You need install Jmeter first, and make sure your Tomcat 8x is runing.
```shell
mvn clean verify
```
To run in command line mode with parameters
```shell
./bin/jmeter.sh -n -t ./tests/CalculatorTestPlan.jmx -Jusers=20 -Jloop=2 -l ./results/calculator_`date +'%y%m%d%H%M%S'`.cs
```
### 1.8 Build Project Site
```shell
mvn site
```
## 2. Automaticly Build, Test, and Deploy By Jenkins

### 2.1 Create and Configure a Freestyle Jenkins Project
Execute every mvn goal one by one defined in Build Section Step: "Invoke top-level Maven targets"
### 2.2 Create and Configure a Pipeline Jenkins Project
Execute the Jenkins Pipeline Script File: Jenkinsfile-maven-github
### 2.3 Create and Configure a Freestyle Jenkins Project
Execute every az script one by one defined in Build Section Step: "Execue shell"

## 3. Containerize Your Web App
1. Build a docker image using `Dockerfile`:
   ```
   docker build -t calculator .
   ```
2. Run docker image locally
   ```
   docker run --rm -p 8080:8080 calculator
   ```
3. Then you can access the web app at http://localhost:8080/api/calculator/ping in browser

4. Stop container
   ```
   docker ps -a
   docker stop <Container-ID>
   ```
5. Push your local image to your docker hub repositories
   ```
   docker login -u <Your-Docker-ID> -p <Your-Docker-Password>
   docker tag calculator <Your-Docker-ID>/calculator
   docker push <Your-Docker-ID>/calculator
   ```
## 4. Deploy to Azure Web App using Container Image
1. Create a Web App in Linux on Azure
2. Save the changes and you'll be able to access the web app in a few seconds.

## 5. Deploy to Your DockerHub RepoAzure Web App using Container Image
1. Create a Container Registry on Azure
2. Push your local image to ACR:
   ```
   docker login <Your-ACR-Login-Server> -u <Your-ACR-Username> -p <Your-ACR-Password>
   docker tag calculator <<Your-ACR-Login-Server>/calculator
   docker push <Your-ACR-Login-Server>/calculator
   ```
3. Create a Web App in Linux on Azure
4. In Docker Container settings of Web App, fill in image name, server URL, username and password of your ACR.
5. Save the changes and you'll be able to access the web app in a few seconds.
