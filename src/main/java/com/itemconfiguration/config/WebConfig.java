package com.itemconfiguration.config;

import com.itemconfiguration.config.properties.MultiTenantRboProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(MultiTenantRboProperties.class)
public class WebConfig implements WebMvcConfigurer{

	private static final String PROPERTY_CORS_ALLOWED_ORIGIN = "cors.allower-origin";

	private Environment env;

	public WebConfig(Environment env) {
		this.env = env;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("POST", "GET", "DELETE")
				.allowedOrigins(env.getProperty(PROPERTY_CORS_ALLOWED_ORIGIN))
				.allowCredentials(true);
	}
}
