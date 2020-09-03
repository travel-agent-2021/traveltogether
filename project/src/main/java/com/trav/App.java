package com.trav;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.trav"})
@MapperScan("com.trav.mapper")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
        System.out.println("daf");
    }
}
