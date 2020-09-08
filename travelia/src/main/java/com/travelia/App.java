package com.travelia;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.travelia"})
@MapperScan("com.travelia.mapper")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
