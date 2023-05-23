package com.pensatocode.example;

import com.pensatocode.example.db.ArticleRepository;
import com.pensatocode.example.health.ApplicationHealthCheck;
import com.pensatocode.example.resources.ArticleResource;
import com.pensatocode.example.resources.LoopbackResource;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MyExampleApplication extends io.dropwizard.Application<BasicConfiguration> {

    /**
     * The main method is responsible for running the application.
     * We could either pass the args to the run method or fill it by ourselves.
     * The first argument can be either server or check.
     * The check option validates the configuration, while the server option runs the application.
     * The second argument is the location of the configuration file.
     */
    public static void main(final String[] args) throws Exception {
        // Firstly,
        new MyExampleApplication().run("server", "basic-config.yml");
    }

    /**
     * The initialize method sets the configuration provider to the ResourceConfigurationSourceProvider,
     * which allows the application to find a given configuration file in the resource directory.
     * It isn't obligatory to override this method.
     */
    @Override
    public void initialize(final Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());

//        bootstrap.addBundle(GuiceBundle.builder()
//                .enableAutoConfig("com.pensatocode.example.resources", "com.pensatocode.example.db")
//                .build());

        super.initialize(bootstrap);
    }

    @Override
    public String getName() {
        return "dropwizard-filter-example";
    }

    @Override
    public void run(final BasicConfiguration configuration, final Environment environment) {
        // health checks
        environment.healthChecks().register("application", new ApplicationHealthCheck());
        // register resources
        environment.jersey().register(new ArticleResource(configuration.getDefaultSize(), new ArticleRepository()));
        environment.jersey().register(new LoopbackResource());
    }

}
