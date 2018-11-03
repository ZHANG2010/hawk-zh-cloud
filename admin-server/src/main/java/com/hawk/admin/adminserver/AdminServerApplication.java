package com.hawk.admin.adminserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * AdminServerApplication
 *
 * @author hawk_zhang
 * @date 2018/10/22
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.hawk.admin.adminserver.mapper" )
public class AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}
}
