package com.devsu.microservice.client.api.test;

import org.testcontainers.containers.MySQLContainer;

public abstract class TestContainersConfig {

    static final MySQLContainer<?> mysqlContainer;

    static {
        mysqlContainer = new MySQLContainer<>("mysql:8.0.26")
                .withDatabaseName("test")
                .withUsername("user")
                .withPassword("password");
        mysqlContainer.start();

        System.setProperty("spring.datasource.url", mysqlContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysqlContainer.getUsername());
        System.setProperty("spring.datasource.password", mysqlContainer.getPassword());
    }
}
