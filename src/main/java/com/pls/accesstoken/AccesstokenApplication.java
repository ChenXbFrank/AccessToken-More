package com.pls.accesstoken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//定时器需要导入
//@EnableScheduling
//mapper/dao需要导入
@MapperScan(basePackages = "com.pls.accesstoken.dao")
public class AccesstokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccesstokenApplication.class, args);
	}
}
