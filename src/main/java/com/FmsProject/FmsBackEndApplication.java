package com.FmsProject;

import java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.FmsProject")
@ComponentScan("com.FmsProject")
public class FmsBackEndApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FmsBackEndApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FmsBackEndApplication.class, args);
	}

	// @Bean
	// public CorsFilter corsFilter() {
	// CorsConfiguration corsConfiguration = new CorsConfiguration();
	// corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
	// corsConfiguration.setAllowedHeaders(
	// Arrays.asList("Origin", "Access-Control-Allow-Origin", "Requestor-Type",
	// "Content-Type",
	// "Accept", "Authorization", "X-Requested-With", "x-locale",
	// "Access-Control-Request-Method", "Access-Control-Request-Headers"));
	// corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type",
	// "Requestor-Type", "Accept",
	// "Authorization", "X-Get-Header",
	// "Access-Control-Allow-Origin", "Access-Control-Allow-Origin",
	// "Access-Control-Allow-Credentials"));
	// corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
	// "DELETE", "OPTIONS"));
	// UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new
	// UrlBasedCorsConfigurationSource();
	// corsConfiguration.setAllowCredentials(true);
	// urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
	// corsConfiguration);
	// return new CorsFilter(urlBasedCorsConfigurationSource);
	// }

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}