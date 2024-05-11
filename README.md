Simple CRUD backend app for testing purposes.

In order to run the app you'll need Maven and Docker installed on your machine (Docker Desktop for Windows).

Steps to run the app:
1) Clone the project into a directory of your preference
2) Move to the project directory ```cd SpringBootRESTApi/```
3) Run command ```mvn clean install && docker build -t springboot-app .```
4) Run command ```docker images``` to check that docker image 'springboot-app' has been successfully built form Dockerfile
5) Run command ```docker run -d -p 8989:8989 --name springboot-app springboot-app```

Swagger:
http://localhost:8989/swagger-ui/index.html
