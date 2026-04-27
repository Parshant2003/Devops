Docker file - 
1. a file that define how docker image is created 
2. set of instruction


Spring boot basic docker file

FROM eclipse-temurin:21-jre-alpine                                (run time environment)
WORKDIR /app                                       (working directory inside a container)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]             (which command to run when container starts)
RUN                                                 (execute command while building)

----------------------------------------------------------------------------------------------------
Dockerfiles: Java
Java Spring Boot
text
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
Node.js (Express API)
text
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json .
RUN npm ci --only=production

COPY . .
RUN npm run build


Node.js
FROM node:20-alpine
WORKDIR /app
COPY --from=build /app/dist ./dist
COPY --from=build /app/node_modules ./node_modules
EXPOSE 3000
CMD ["node", "dist/server.js"]
Python (FastAPI)
text
FROM python:3.12-slim AS build
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .
RUN pip install .


Python

FROM python:3.12-slim
WORKDIR /app
COPY --from=build /app/*.whl . || true
COPY --from=build /usr/local/lib/python3.12/site-packages /usr/local/lib/python3.12/site-packages
COPY . .
EXPOSE 8000
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]




 Build a image 
  docker build -t parshantbisht2003/hello-springboot .    (locally)
 Make container
 docker run -d --name springContainer_1 -p 8080:8080 parshantbisht2003/hello-springboot

 
