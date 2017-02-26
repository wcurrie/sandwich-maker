# Zipkin Experiment

What is zipkin: [one explanation](http://ryanjbaxter.com/cloud/spring%20cloud/spring/2016/07/07/spring-cloud-sleuth.html)

Couple of spring boot services with silly names: butcher, baker, candlestickmaker and kitchenhand.

## How?

Made a sandwich in docker using:
1. mvn clean package
2. docker-compose up
3. curl -XPOST localhost:8080/sandwiches

Look at zipkin trace by visiting http://localhost:9411. Grab a trace ID from the docker logs. Eg 6fe09cac9d9b37d3 in:

    kitchenhand_1       | 2017-02-26 04:49:32.757  INFO [kitchenhand,6fe09cac9d9b37d3,6fe09cac9d9b37d3,true] 1 --- [readScheduler-2] c.e.k.controller.SandwichController      : Collecting ham

Compare with curl -XPOST localhost:8080/sandwiches?turbo=true (tip: it's not actually much faster)

## Resources

https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin

https://github.com/spring-cloud/spring-cloud-sleuth

Collecting traces
Use https://github.com/openzipkin/zipkin/tree/master/zipkin-server

    wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'
    java -jar zipkin.jar
    
https://github.com/openzipkin/docker-zipkin    
    
### Eureka
https://spring.io/blog/2015/01/20/microservice-registration-and-discovery-with-spring-cloud-and-netflix-s-eureka

### Other Directions

* https://github.com/openzipkin/zipkin-aws
* Converting a trace into plantuml sequence diagram
* Try spring 5 and webflux
* Put each service in an alpine docker container, start them all using docker compose