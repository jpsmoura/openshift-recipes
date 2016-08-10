# Camel ActiveMQ client with Spring

### Description
Demonstrates usage of Camel Spring DSL, Fabric8 MQ client.

Creates a new project, and gives the default service account view role. That is needed for A-MQ Kube discovery protocol to query Kubernetes.
The example docker container, expects environment variables for the username and password to connect to A-MQ.

### Start an ActiveMQ Broker

    oc new-project amq-demo

    oc policy add-role-to-user view system:serviceaccount:amq-demo:default

    oc process -f amq62-basic.json -v MQ_USERNAME=admin,MQ_PASSWORD=admin | oc create -f -

### Build the project

    mvn clean install -s configuration/settings.xml
    mvn -Pf8-local-deploy -s configuration/settings.xml
