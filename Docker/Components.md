Docker Components


Dockerfile-Intruction to build decked image

Docker Images-these are the templatesthat defines the container and its dependency.

Containers-
        are the run time environments created from docker images.
        running instance of the docker image.



Image Layer- Every image is made up of multiple layer 
Why Layers???
Rebuild are faster,can perform cache

Docker Registry:- It is a place where docker image are stored remotely.


Docker tags: name assigned to a docker image version 
 eg myapp:latest
    myapp:prod
    myapp:dev
    [image_name]:[name]

Docker Engine:
  Run time that manage and runs a container.


Docker Components
Dockerfile
Instruction to build Docker image

Docker Images
Templates that define the container and its dependencies

Containers
Runtime environments created from Docker images

Running instance of the Docker image

![Flow diagram](<../images/Screenshot 2026-04-21 195941.png>)

Image Layers
Every image is made up of multiple layers

Why Layers?

Rebuilds are faster

Can perform caching


Docker Registry
Place where Docker images are stored remotely

![Docker Registry](<../images/Screenshot 2026-04-21 200549.png>)


Docker Tags
Name assigned to a Docker image version
Example
myapp:latest
myapp:prod  
myapp:dev
[image_name]:[tag_name]

Docker Engine
Runtime that manages and runs containers