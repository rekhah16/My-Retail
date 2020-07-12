## myRetail RESTful service
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

#### Tech Stacks
Spring Boot,
MongoDB,
Docker,
DockerSwarm

#### System Design

![Image](https://github.com/rekhah16/My-Retail/blob/master/system-design.png)
#### Setup mongodb
Install and start mongodb by following instructions here https://github.com/mongodb/homebrew-brew
Default port 27017

#### Maven build and run the sprintboot app
$cd /myretail \
$mvn clean install \
$java -jar /myretail-docker.jar

#### Docker build and run with docker swarm
$docker build -t myretail-docker . \
$docker stack deploy --compose-file docker-compose.yml myretail 

check the pods running \
$docker ps 

#### API Testing using postman
[a link](https://github.com/rekhah16/My-Retail/blob/master/myretail.postman_collection.json)











