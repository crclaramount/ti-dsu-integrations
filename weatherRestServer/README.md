## General Info:
The server side was create with Java Maven and was configure for to be deploy on WildFly (Jboss)
# Steps for to deployd:

## Step 1:
Istall all the libraries and dependencies:
```sh
mvn clean install
```
## Step 2
find the wildfly installation folder,
```sh
mvn compile
```
## Step 3
find the wildfly installation folder and there go to the "standalone" folder, there go to the "Deployments" folder
and paste the compiled project (project.war) and the folder (these are in the generate Target Folder in the step 2)

## Step 4
run the standalone-full.xml Script
```sh
./standalone.sh -c standalone-full.xml
```

with that we will deploy the server, and it will be ready to listening petitions

