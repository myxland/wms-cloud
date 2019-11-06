package com.zlsrj.wms.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages= {"com.zlsrj.wms"})
@ComponentScan(basePackages= {"com.zlsrj.wms"})
public class IamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

}
