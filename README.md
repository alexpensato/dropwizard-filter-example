# dropwizard-filter-example
Dropwizard example on HTTP Request Filters and Annotations


How to start the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizfilters-1.0-SNAPSHOT.jar`
1. To check that your application is running enter url `http://localhost:8080`


Troubleshooting
---
For errors that occur during the building stage of the application:
To see the full stack trace of the errors, run Maven with the -e switch: `mvn clean install -e`
To enable the full debug logging, run Maven with the -X switch: `mvn clean install -X`


Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Creating the Project
---

This project was created using an old version of Dropwizard (2.1.6) with the following command:
mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=2.1.6

Follow this tutorial to create a new project with a more current version of Dropwizard:
https://www.dropwizard.io/en/latest/getting-started.html


Docs
---
https://www.dropwizard.io/en/latest/manual/core.html
https://www.dropwizard.io/en/latest/manual/configuration.html
https://www.dropwizard.io/en/latest/manual/core.html#filters

Guicey:
https://mvnrepository.com/artifact/ru.vyarus/dropwizard-guicey/5.9.0
https://xvik.github.io/dropwizard-guicey/5.9.0/getting-started/
https://github.com/xvik/dropwizard-guicey/tree/dw-2.1/examples/core-getting-started
https://xvik.github.io/dropwizard-guicey/5.9.0/getting-started/#adding-a-filter
