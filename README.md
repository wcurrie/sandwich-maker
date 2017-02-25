## Resources

https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin

https://github.com/spring-cloud/spring-cloud-sleuth

Collecting traces
Use https://github.com/openzipkin/zipkin/tree/master/zipkin-server

    wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'
    java -jar zipkin.jar
    
### Eureka
https://spring.io/blog/2015/01/20/microservice-registration-and-discovery-with-spring-cloud-and-netflix-s-eureka