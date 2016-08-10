# Camel Spring Boot Config Map Example

###CONFIG MAP
oc create configmap demo --from-file=service.properties
oc edit configmap demo
oc describe configmap demo

oc rsh $(oc get pods -l component=fis-camel-spring-boot-demo --template '{{range .items}}{{.metadata.name}}{{end}}') cat /etc/config/service.properties
This quickstart run Apache Camel in a standalone Java Spring Boot container.
It is based on Fuse 6.2 hence Camel 2.15.1 and corresponding Spring version.




### Option 1: Building (with hawt-app-maven-plugin) and running locally

    mvn clean install -s configuration/settings.xml
    target/hawt-app/bin/run.sh

### Option 2: Building (with spring-boot-maven-plugin) and running locally

    mvn clean install -s configuration/settings.xml -Pspring-boot-maven-plugin
    mvn spring-boot:run -s configuration/settings.xml -Pspring-boot-maven-plugin

### Running the example in OpenShift
The example can be built and deployed using a single goal:

    mvn -Pf8-deploy

### Running the example using OpenShift S2I template
The example can also be built and run using the included S2I template quickstart-template.json.

    oc new-app -f quickstart-template.json


