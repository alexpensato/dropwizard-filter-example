# dropwizard-filter-example
Dropwizard example on HTTP Request Filters and Annotations


How to start the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizfilters-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`


Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Creating the Project
---

This project was created using an old version of Dropwizard (2.1.6) with the following command:
mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=2.1.6

Follow this tutorial to create a new project with a more current version of Dropwizard:
https://www.dropwizard.io/en/latest/getting-started.html
