package com.zdnf.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zdnf.bbs.dao")
public class SkyisbuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyisbuleApplication.class, args);
	}
}
