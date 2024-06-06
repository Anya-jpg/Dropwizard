package com.flipkart;


import com.flipkart.resource.AuthorResource;
import com.flipkart.resource.StatusResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import com.flipkart.resource.HealthCheckResource;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import org.skife.jdbi.v2.DBI;
import com.flipkart.service.UserService;

public class mavenApplication extends Application<mavenConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(mavenApplication.class);
    private static final String SQL = "sql";
    private static final String DROPWIZARD_MYSQL_SERVICE = "Dropwizard MySQL Service";

    public static void main(String[] args) throws Exception {
        new mavenApplication().run("server", "com/flipkart/config.yml");
    }

    @Override
    public void initialize(Bootstrap<mavenConfiguration> b) {
    }

    @Override
    public void run(mavenConfiguration config, Environment env) throws Exception {
        // Datasource configuration
        final DataSource dataSource =
                config.getDataSourceFactory().build(env.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        // Register Health Check
        HealthCheckResource healthCheck =
                new HealthCheckResource(dbi.onDemand(UserService.class));
        env.healthChecks().register(DROPWIZARD_MYSQL_SERVICE, healthCheck);
        logger.info("Registering RESTful API resources");
        env.jersey().register(new StatusResource());


        env.jersey().register(new AuthorResource(dbi.onDemand(UserService.class)));
    }
}
