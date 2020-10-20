package com.aaa.sbm;

import com.aaa.sbm.iocdemo.Person;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.aaa.sbm.dao")  //扫描dao接口
@EnableTransactionManagement  //开启事务
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext aplicationContext = SpringApplication.run(SpringbootMybatisApplication.class, args);
		Person person = (Person)aplicationContext.getBean("person");
		person.fighting();
	}

}
