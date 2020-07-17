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

#### Maven build and run the app
$cd /myretail \
$mvn clean install \
$java -jar /myretail-docker.jar

#### Docker build and run with docker swarm
$ docker swarm init \
Swarm initialized: current node (qzhndg0ywtbfaklfr0uz9qkrd) is now a manager. 

To add a worker to this swarm, run the following command: 

   docker swarm join --token SWMTKN-1-601xc4cv9ujr1a576ki4y22ckmwriff2rwuap4julp62onrold-8gz9moe8l7gsuobz0oahzd0uy 192.168.65.3:2377

To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.

$ docker build -t myretail-docker . \
Sending build context to Docker daemon  36.88MB \
Step 1/4 : FROM openjdk:8 \
 ---> b190ad78b520 \
Step 2/4 : ADD target/myretail-docker.jar /myretail-docker.jar \
 ---> d5aced8339e4 \
Step 3/4 : EXPOSE 8090 \
 ---> Running in 17bd128c29f7 \
Removing intermediate container 17bd128c29f7 \
 ---> ba15a0c0526f \
Step 4/4 : ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=doc", "/myretail-docker.jar"] \
 ---> Running in 0231c20b27d2 \
Removing intermediate container 0231c20b27d2 \
 ---> 64c264d7724b \
Successfully built 64c264d7724b \
Successfully tagged myretail-docker:latest 

$ docker stack deploy --compose-file docker-compose.yml myretail \
Creating network myretail_default 
Creating service myretail_api 

$ docker ps 
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS                  PORTS               NAMES \
baf4848f1b8f        myretail-docker:latest   "java -jar -Dspring.…"   1 second ago        Up Less than a second   8090/tcp            myretail_api.1.iftajmxtchnml556f7e0r4hkh \
96978547100f        myretail-docker:latest   "java -jar -Dspring.…"   1 second ago        Up Less than a second   8090/tcp            myretail_api.2.354wa0jrdiw5d190opkawgk14 

$ docker logs baf4848f1b8f

$ docker exec -it baf4848f1b8f bash

$ docker stats \
CONTAINER ID        NAME                                       CPU %               MEM USAGE / LIMIT     MEM %               NET I/O             BLOCK I/O           PIDS \
216a5a5e16bd        myretail_api.2.6ajwg9x28i87rxausg3xb6idn   0.44%               311.8MiB / 1.944GiB   15.66%              97.5kB / 47.9kB     0B / 0B             34 \
baf4848f1b8f        myretail_api.1.iftajmxtchnml556f7e0r4hkh   0.40%               362.7MiB / 1.944GiB   18.22%              115kB / 54.6kB      0B / 0B             35 


#### API Testing using postman
[https://github.com/rekhah16/My-Retail/blob/master/myretail.postman_collection.json](https://github.com/rekhah16/My-Retail/blob/master/myretail.postman_collection.json)

##### Get API
```
curl -X GET \
  http://localhost:8080/products/13860428 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 1fe78233-ef68-452c-9fdf-e1a90896af66' \
  -H 'cache-control: no-cache'
  
  Response Status:200 OK
  {
    "id": "13860428",
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 78.98,
        "currency_code": "USD"
    }
}


curl -X GET \
  http://localhost:8080/products/5555 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 235a862f-efa0-4b1b-b74c-d47c4561a9b2' \
  -H 'cache-control: no-cache'


Response Status: 404
{
    "status": "404 NOT_FOUND",
    "message": "Error while calling api redsky.target.com"
}

curl -X GET \
  http://localhost:8090/products/16696652 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 86fa0092-dc3d-4ad1-90c8-a26eec778dad' \
  -H 'cache-control: no-cache'
  
{
    "status": "404 NOT_FOUND",
    "message": "Requested product data not found"
}

```
##### PUT API
```
curl -X PUT \
  http://localhost:8080/products/13860428 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 481bd85e-c731-4763-ade2-dea8f49493ca' \
  -H 'cache-control: no-cache' \
  -d '{
    "id": "13860428",
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 78.98,
        "currency_code": "USD"
    }
}'

Response code: 200 OK

curl -X PUT \
  http://localhost:8080/products/3444 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a031c29f-763c-41d6-8502-876658e3516e' \
  -H 'cache-control: no-cache' \
  -d '{
    "id": "1386048",
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 78.98,
        "currency_code": "USD"
    }
}'

{
    "status": "400 BAD_REQUEST",
    "message": "incorrect product id"
}
```










