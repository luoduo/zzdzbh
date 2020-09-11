package com.zzhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@SpringBootApplication(scanBasePackages = "com.zzhl")
//@EnableSwagger2
public class WebApplicationClient {

	

	public static void main(String[] args) {
		SpringApplication.run(WebApplicationClient.class, args);
	}


}



