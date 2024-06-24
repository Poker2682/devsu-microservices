package com.devsu.microservice.account.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.devsu.microservice")
@EnableFeignClients
@EnableAsync
public class AccountTransactionMicroserviceApplication {


    public static void main(String[] args) {
        SpringApplication.run(AccountTransactionMicroserviceApplication.class, args);
    }
}
