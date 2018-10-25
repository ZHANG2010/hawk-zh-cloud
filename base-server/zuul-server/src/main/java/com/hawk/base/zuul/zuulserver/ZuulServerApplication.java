package com.hawk.base.zuul.zuulserver;

import com.hawk.base.zuul.zuulserver.filter.AccessTokenFilter;
import com.hawk.base.zuul.zuulserver.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * ZuulServerApplication
 *
 * @author hawk_zhang
 * @date 2018/10/22
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}

	@Bean
	public AccessTokenFilter accessTokenFilter(){
		return new AccessTokenFilter();
	}
	@Bean
	public MyFilter myFilter(){
		return  new MyFilter();
	}
}
