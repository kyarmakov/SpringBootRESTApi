Simple CRUD backend app for testing purposes with in-memory H2 database.

To run the app you'll need JDK-17, Maven and Docker installed on your machine.

Steps to run the app:
1) Clone the project into a directory of your preference
2) Move to the project directory ```cd SpringBootRESTApi/```
3) Run command ```mvn clean install && docker build -t springboot-app .```
4) Run command ```docker images``` to check that docker image 'springboot-app' has been successfully built form Dockerfile
5) Run command ```docker run -d -p 8989:8989 --name springboot-app springboot-app```

Swagger:
http://localhost:8989/swagger-ui/index.html

Steps to access database:
1) Go to ```http://localhost:8989/h2-console```
2) Make sure that JDBC URL is set to ```jdbc:h2:mem:testdb```
3) Press 'Connect' button without changing 'User Name' and 'Password'

When the app is restarted so is the DB.
