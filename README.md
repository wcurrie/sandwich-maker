# Zipkin Experiment

Couple of spring boot services with silly names: butcher, baker, candlestickmaker and kitchenhand.

## Resources

https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin

https://github.com/spring-cloud/spring-cloud-sleuth

Collecting traces
Use https://github.com/openzipkin/zipkin/tree/master/zipkin-server

    wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'
    java -jar zipkin.jar
    
### Eureka
https://spring.io/blog/2015/01/20/microservice-registration-and-discovery-with-spring-cloud-and-netflix-s-eureka

### Other Directions

* https://github.com/openzipkin/zipkin-aws
* Converting a trace into plantuml sequence diagram
* Try spring 5 and webflux
* Put each service in an alpine docker container, start them all using docker compose