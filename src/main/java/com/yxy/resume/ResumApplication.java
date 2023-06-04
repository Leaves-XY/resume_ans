package com.yxy.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class ResumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumApplication.class, args);
    }

}
