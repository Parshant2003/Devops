Docker -package your app and container it.
Kubernates- run and manage these reliably at scale. 

K8s Cluster = Control Pane (Brain...) + Worker Nodes (workers...)

Pod - wap a container

Control Pane:-
API Server - All the commands go through API Srvers(command line,CI/CD).
Scheduler - decides which node should run a pod.
Controller Manager - keep track weather pod are ruunning correctly ,if crashes re-deployment
etcd - memory or database inside control pane (state store)

Worker Nodes
Kublets:- control pane communicate 
kube-proxy :- Networking
container runtime:- runs pod/container
pods- contains one or multiple containers 


Repica/Instances :- multiple copies of a particular pod....



                    USER / CUSTOMER
                           |
                           v
                    +---------------+
                    | Load Balancer |
                    +---------------+
                           |
                           v
                    +---------------+
                    |    Service    |   <-- Stable address
                    +---------------+
                      /     |      \
                     /      |       \
                    v       v        v
              +--------+ +--------+ +--------+
              | Pod 1  | | Pod 2  | | Pod 3  |  <-- booking-service replicas
              +--------+ +--------+ +--------+
                   \         |         /
                    \        |        /
                     -----------------
                             |
                             v
                    +------------------+
                    |   Deployment      | <-- “3 pods always running”
                    +------------------+

========================================================

                KUBERNETES CLUSTER (Whole System)
--------------------------------------------------------

        +------------------- CONTROL PLANE -------------------+
        |                                                     |
        |  +-------------+   +-------------+   +----------+  |
        |  | API Server  |   | Scheduler   |   |  etcd    |  |
        |  | Entry Gate  |   | Pod place   |   | DB       |  |
        |  +-------------+   +-------------+   +----------+  |
        |                                                     |
        +-----------------------------------------------------+

                         |
                         v

        +-------------------- WORKER NODES -------------------+

        +------------------+     +------------------+
        | Node 1           |     | Node 2           |
        | (Machine)        |     | (Machine)        |
        |  +------------+  |     |  +------------+  |
        |  | Pod         |  |     |  | Pod         | |
        |  | booking     |  |     |  | payment     | |
        |  +------------+  |     |  +------------+  |
        +------------------+     +------------------+