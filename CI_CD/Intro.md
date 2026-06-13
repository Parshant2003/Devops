CI/CD= steps+ oders + automation 

1. Work flow
2. Jobs 
3. Steps
4. Runner 

code--> test --> build --> Package --> push --> deploy --> verify 

COMMANDS
- name: Say Hello
 run: echo "Hello from CI" 

Prebuilt Action
- name: CheckOut Code
  uses: action/checkout@v4


Environment :-
env:
     - name: Install
       env:
        APP_ENV: "Suiiiii"
        run: echo "Environment -----> $APP_ENV"   

Secret:-
 - name: Install
      env:
        APP_ENV: "Dev"
        DB_PASSWORD: ${{ secrets.DB_PASSWORD }}
      run: echo "Environment -----> $DB_PASSWORD" 
 
 
Inputs and parameters :-
- name: Download Artifact
  uses: actions/download-artifact@v4
  with:
    name: spring-boot-jar    # Upload wala naam
    path: downloaded-jar/    # Save karne ka path

Dependency and flow:-


--------------------CD---------------------------------

Continuous Deploynment:-Every successful build is automatically deployed to an environment without manual interventions

How CI/CD talk to k8s???

how kubectl know k8s cluster??
kubeconfig (config file) ----- cluster information
                 User Information & credentials
                 Context Information (gives flexibility eg as a user today i want to connect to Aws cluser, next i want to conect to GCP/Asure ---> can switch clusters)
                 Switching Env
                 Automation
                 Security
command:-
kubectl config view
kubectl config current-context
kubectl config use-context <name>

When kubeconfg created??

Docker Desktop:(local k8s)
Automatically when we enable Kubernates in DD.

AWS(EKS)
when cluster created automatically- bts

aws eks update-kubeconfig --name <Cluer_name> --region ap-south-1
