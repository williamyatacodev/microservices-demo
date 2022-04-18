<h1>
  Project Demo Microservice
  <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="30px"/>
</h1>

### :man_technologist: Description

The project was carried out under the hexagonal architecture.

Contains:
- Server Eureka 
- Server Gateway
- One Microservice

Steps for run the project locally.

- Eureka Server
   - Enter the directory of the eureka-service project and execute the command:

          mvn spring-boot:run

- Server Eureka
   - Enter the directory of the gateway-service project and execute the command:

          gradle bootRun
          
- One Microservice
    - Add ENVIRONMENT to access to Database MySQL
          
          MYSQL_HOST: Host MYSQL
          MYSQL_PORT: Port MYSQL
          MYSQL_DATABASE: name database 
          MYSQL_USERNAME: username MYSQL
          MYSQL_PASSWORD: password MYSQL
          
    - Enter the directory of the test-service project and execute the command:

          gradle bootRun

- Test Endpoint GET **runTest**
    - **username** and **password** is User API to Finero
    - ENDPOINT is secured with Basic Authentication with user in Memory **user:demo** **pass:demo**

          curl -i --user demo:demo -X GET "http://localhost:8080/runTest?username=XXXXXXX&password=XXXXXX" -H "accept: application/json"
      
