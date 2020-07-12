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










