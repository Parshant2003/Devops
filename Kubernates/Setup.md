K8s:- group of maching running kubernates

Set up locally :
1. Docker desktop
2. Minikube
3. kind

kubectl version --client
kubectl get nodes
kubectl config get-contexts  (where is my k8s pointing to)
kubectl config use-contexts "<----------docker-desktop/minikube/kind-------- >"
kubectl get pods
kubectl get pods -w   //in watch mode 
 


------------------Pods and deploynments-----------------------------

Control manager vs deployment 
deployment - as a devloper i will write a process for deployment in yaml files.
control manager-follow the instruction keep a check every thing working fine or not.  

Service in k8s:-
pod are not directly accessible to outer world so service are created 
with the help of (NodePort)

Srevice Types:-
NodePort -> Har node (server) ke IP pe ek specific port khol deta hai 
LoadBalancer->Cloud provider (AWS, Azure, GCP) ka load balancer provision karta hai.
ClusterIP -> only assesible with in the service. microservice ([user-service]----[booking-service])
ExternalName ->

Flow 
Browser-->NodePort-->Services-->pods-->containers.


kublectl create deployment web --image=nginx 
kubectl expose deployment web --type=NodePort --port=80
kubectl get svc
kubectl get svc <web>

kubectl delete pod <name_deployment>    // delete it ---> recreate it ---> re-run it 
kubectl get rc      // give replica set
kubectl describe rc <name of deployment> // description of replica set 

Scaling (pod) 
kubectl scale deployment web --replicas=5

Debugging a Pod (Commands)
kubectl logs <web-64c966cf88-bxfft>
kubectl exec -it <poddddd> -- sh       // inside the particular pod 

Dealing with yaml
kubectl get deployment web -o yaml

kubectl apply -f app.yaml
kubectl apply -f service.yaml
kubectl delete -f service.yaml 

-------------------------------------------------
env:
        - name: APP_MESSAGE
          value: "Hello I am from app.yaml"
        - name: DB_Password
          value: "password123"
-------------------------------------------------

config map - insensitive data
secrets - sensitive information ----> must be base 64(encoded)...

