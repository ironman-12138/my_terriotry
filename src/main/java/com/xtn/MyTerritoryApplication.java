package com.xtn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.xtn.**.mapper")
@SpringBootApplication
public class MyTerritoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTerritoryApplication.class, args);
    }

}
