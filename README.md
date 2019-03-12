# A Java Maven Calculator Web App
A Java calculator web app, build by Maven, CI/CD by Jenkins.

![image](./realworld-pipeline-flow.png)

## 1. Manualy Build, Test, and Deploy By Maven

### 1.1 Start Nexus (Optional)
```console
$ cd ~/sonatype/nexus/bin
$ ./nexus start 
```
Visit http://localhost:8081/ with admin/admin123.

### 1.2 Build
```console
$ mvn clean package
...
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.qianhong.calculator.CalculatorServiceTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.06 s - in com.qianhong.calculator.CalculatorServiceTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] >>> cobertura-maven-plugin:2.7:cobertura (cobertura) > [cobertura]test @ java-maven-calculator-web-app >>>
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ java-maven-calculator-web-app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/maping/code/test/java-maven-calculator-web-app/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ java-maven-calculator-web-app ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- cobertura-maven-plugin:2.7:instrument (cobertura) @ java-maven-calculator-web-app ---
...
[INFO] Packaging webapp
[INFO] Assembling webapp [java-maven-calculator-web-app] in [/Users/maping/code/test/java-maven-calculator-web-app/target/calculator]
[INFO] Processing war project
[INFO] Copying webapp resources [/Users/maping/code/test/java-maven-calculator-web-app/src/main/webapp]
[INFO] Webapp assembled in [71 msecs]
[INFO] Building war: /Users/maping/code/test/java-maven-calculator-web-app/target/calculator.war
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  38.163 s
[INFO] Finished at: 2019-03-06T21:35:57+08:00
[INFO] ------------------------------------------------------------------------
```
>Attention: Due to China GFW, you may fail when you build this project. Try it outside China GFW.

### 1.3  Run Locally
```console
$ mvn jetty:run
[INFO] Scanning elapsed time=147ms
[INFO] DefaultSessionIdManager workerName=node0
[INFO] No SessionScavenger set, using defaults
[INFO] node0 Scavenging every 660000ms
[INFO] Started o.e.j.m.p.JettyWebAppContext@48535004{Calculator Web,/calculator,file:///Users/maping/code/java-maven-calculator-web-app/src/main/webapp/,AVAILABLE}{file:///Users/maping/code/java-maven-calculator-web-app/src/main/webapp/}
[INFO] Started ServerConnector@580fd26b{HTTP/1.1,[http/1.1]}{0.0.0.0:9999}
[INFO] Started @3779ms
[INFO] Started Jetty Server
```
By default, the jetty port is 9999, so you should visit following urls in browser:
- http://localhost:9999/calculator/api/calculator/ping
- http://localhost:9999/calculator/api/calculator/add?x=8&y=26
- http://localhost:9999/calculator/api/calculator/sub?x=12&y=8
- http://localhost:9999/calculator/api/calculator/mul?x=11&y=8
- http://localhost:9999/calculator/api/calculator/div?x=12&y=12

To run in a different port, `mvn jetty:run -Djetty.port=<your port>`

### 1.4 Debug Locally
```shell
$ set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n
$ mvn jetty:run
```
### 1.5 Run JUnit Test
```shell
mvn clean test
```
### 1.6 Run Integration Test
```shell
mvn clean integration-test
```
### 1.7 Deploy Your Web App to An Existed Tomcat 8x
You need change pom.xml, point to your Tomcat 8x.
```shell
mvn cargo:run
```
### 1.8 Run Performance Test with JMeter
You need install Jmeter first, and make sure your Tomcat 8x is runing.
```shell
mvn clean verify
```
To run in command line mode with parameters
```shell
./bin/jmeter.sh -n -t ./tests/CalculatorTestPlan.jmx -Jusers=20 -Jloop=2 -l ./results/calculator_`date +'%y%m%d%H%M%S'`.cs
```
### 1.9 Build Project Site
```shell
mvn site
```
## 2. Automaticly Build, Test, and Deploy By Jenkins

### 2.1 Create and Configure a Freestyle Jenkins Project
Project name: MyJavaMavenCalculateWebApp

Execute every mvn goal one by one defined in Build Section Step: "Invoke top-level Maven targets"
### 2.2 Create and Configure a Freestyle Jenkins Project, using Publish Over FTP plugin
Project name: MyJavaMavenCalculateWebApp-AzureAppService-FTP
### 2.3 Create and Configure a Freestyle Jenkins Project, using Azure App Service plugin
Project name: MyJavaMavenCalculateWebApp-AzureAppService
### 2.4 Create and Configure a Pipeline Jenkins Project
Project name: MyJavaMavenCalculateWebApp-Pipeline

Execute the Jenkins Pipeline Script File: Jenkinsfile

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
## 4. Deploy to Azure Web App using Container Image in Docker Hub
1. Create a Web App in Linux on Azure
2. Save the changes and you'll be able to access the web app in a few seconds.

## 5. Deploy to Your Azure Web App using Container Image in ACR
1. Create a Container Registry on Azure
2. Push your local image to ACR:
   ```
   docker login <Your-ACR-Login-Server> -u <Your-ACR-Username> -p <Your-ACR-Password>
   docker tag calculator <Your-ACR-Login-Server>/calculator
   docker push <Your-ACR-Login-Server>/calculator
   ```
3. Create a Web App in Linux on Azure
4. In Docker Container settings of Web App, fill in image name, server URL, username and password of your ACR.
5. Save the changes and you'll be able to access the web app in a few seconds.

## Reference
- [Jenkins Pipeline](https://jenkins.io/doc/book/pipeline/)


