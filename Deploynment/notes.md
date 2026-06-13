Deployment- maine ek machine book kar li khareed kar
Concurrency - no of request/users came and out of them how many i can serve at a same time
concurrency- active users
concurrency kisi feature ki bhi ho sakti hai 
RPS(Request per Second) - no of request coming pe second.

-----------------------------------------------------------
LEVEL-1 
users - 1000
rps - 1-20 
tools - PAAS -> Vercel,Render,Railway,Netlify (Suifficent)
        but -> depend on your code quality
               no infinite looping
               ek hi api call pe db call 2-3 time 
Bottleneck -> matlab system ka woh kaam ya hissa jo sabse zyada slow chalta hai aur poori app/flow ko slow kar deta hai , everyting work one one single machine.
-----------------------------------------------------------
LEVEL-2 
user - 1,000-10,000
RPS - 20-400 
tools - render,digital ocean,cloud providers
bottleneck - high cpu , db stress

user - load balancer --- server 1 ------- DB 
                     --- server 2 
------------------------------------------------------------
LEVEL-3
users - 10k-100k
rps - 100-1000
tools - AWS/GCP
bottleneck - database- every req hit db 
Decision - by using caching - 
user -cdn -  load balancer --- server 1 ------- DB 1
                           --- server 2         DB 2 
                                                DB 3
                                                
-----------------------------------------------------------
LEVEL-4 (microservice)/(distributed monolithic archetrecture)
users - 100k-1M
rps - 1K-15k+
tools - /message brokers/node workers/
bottleneck - sync processing, DB write, Latency
-----------------------------------------------------------
LEVEL-5 
users - 1M+
rps - 10k-100k(Global request+unpredictable)
Tools - AWS,GCP,k8s,kafka

