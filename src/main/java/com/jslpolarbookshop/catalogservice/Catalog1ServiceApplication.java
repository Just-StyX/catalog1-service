package com.jslpolarbookshop.catalogservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Catalog1ServiceApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(Catalog1ServiceApplication.class)
				.headless(false)
				.bannerMode(Banner.Mode.OFF)
				.logStartupInfo(false)
				.run(args);

	}

}
