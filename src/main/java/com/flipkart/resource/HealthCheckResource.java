package com.flipkart.resource;
import com.codahale.metrics.health.HealthCheck;
import com.flipkart.service.UserService;


public class HealthCheckResource extends HealthCheck {

    private static final String HEALTHY_MESSAGE = "The Dropwizard blog Service is healthy for read and write";
    private static final String UNHEALTHY_MESSAGE = "The Dropwizard blog Service is not healthy. ";

    private final UserService userService;

    public HealthCheckResource(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result check() throws Exception {
        String mySqlHealthStatus = userService.performHealthCheck();

        if (mySqlHealthStatus == null) {
            return Result.healthy(HEALTHY_MESSAGE);
        } else {
            return Result.unhealthy(UNHEALTHY_MESSAGE , mySqlHealthStatus);
        }
    }
}