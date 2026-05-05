CI/CD= steps+ oders + automation 

1. Work flow
2. Jobs 
3. Steps
4. Runner 
 
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
