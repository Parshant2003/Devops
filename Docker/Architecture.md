1. Docker deamon - heart of the docker it perform a lot of function.
2. Docker API - help to send request to docker deamon.
3. Docker CLI- run through docker commands 

Flow 
Docker CLI ---> Docker API ---> Docker Daemon

All this combined called docker engine.

![Docker Engine](<../images/Screenshot 2026-04-22 183133.png>)

Docker Compose:-
also use to manage containers

Docker Mapping 
command- docker run nginx (if not locally then downloaded from docker hub)
if we access localhost:8080 from browser it will not work 
so 
Port Mapping - we have to map 
command -  docker run -p 8080:80 nginx


docker pull embarkx/hello-spring:latest

docker run -p 8080:8080 embarkx/hello-spring              
docker run -p 8080:8080 embarkx/hello-spring:v1.0.0.1    (for tags)

Life cycle of container 

| Stage      | Command                   | Status  | Kya Hota Hai                                    |
| ---------- | ------------------------- | ------- | ----------------------------------------------- |
| 1. Created | docker create             | Created | Container bana, abhi start nahi                 |
| 2. Running | docker start / docker run | Up      | Container chal raha hai                         |
| 3. Paused  | docker pause              | Paused  | Container temporary freeze (processes ruk gaye) |
| 4. Stopped | docker stop               | Exited  | Container gracefully band (cleanup hua)         |
| 5. Deleted | docker rm                 | GONE    | Container completely delete                     |


docker ps  ---> whaich container is running right now (active)

docker create --name app1 -p 3003:3000 embarkx/hello-node  --->create a new container with name app1

docker restart app1 ----> to restart a container

Logs command:-
docker logs <container_id>or <container_name>
docker logs -f <container_id>or <container_name>   (for live logs)
docker logs <container_id> --tail 10 (last 10 logs)

docker exec -it <container_id> sh

Some more important commands
Image commands:-
1. docker images
2. docker rmi <container_id> (remove image but not container)
   docker rmi -f <container_id> (used to force delete image) 
Container Commands:-
1. docker rm <container_id>   (first need to stop it)
2. docker rm -f <container_id> (force remove it)




