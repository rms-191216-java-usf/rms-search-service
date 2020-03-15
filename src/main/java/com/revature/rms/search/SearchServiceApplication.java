package com.revature.rms.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class SearchServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SearchServiceApplication.class, args);
  }

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(this.getClass().getPackage().getName()))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }
}
