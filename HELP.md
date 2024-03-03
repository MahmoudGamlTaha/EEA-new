# Getting Started

## How to use it as docker
You will need to be familiare with docker commands, to generate a docker image use following:
* To generate docker image with tag name ffs-accounting
```sh
    docker build -t ffs-accounting .
```

* To run docker container and expose the accounting services on port 9191
```sh
    docker run --rm  -dit -p 9191:8080 --name accounting ffs-accounting:latest
```

* To run docker container
```sh
    docker stop accounting
```

* To view docker logs
```sh
    docker logs -f accounting
```

* To remove docker container (N.B. this will not be neccessary if you used *--rm* when docker run command)
```sh
    docker rm accounting
```

* To docker image
```sh
    docker rmi ffs-accounting:latest
```
---
## More links
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

