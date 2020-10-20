package com.aaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.aaa.dao") //扫描dao接口
@EnableTransactionManagement   //开启事务管理
public class Sm1012Application {

    public static void main(String[] args) {
        SpringApplication.run(Sm1012Application.class, args);
    }

}
