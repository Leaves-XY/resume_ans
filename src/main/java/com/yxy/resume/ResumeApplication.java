package com.yxy.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class ResumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class, args);
    }

}
