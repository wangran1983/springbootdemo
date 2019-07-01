package com.donbala;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.donbala.mapper")
@ServletComponentScan
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        System.out.println("programming launching");
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

}
