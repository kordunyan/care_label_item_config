package com.itemconfiguration.config;


import com.itemconfiguration.config.properties.MultiTenantRboProperties;
import com.itemconfiguration.config.properties.RboProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultitenantConfiguration {

	private DataSourceProperties properties;
	private MultiTenantRboProperties rbosProperties;

	public MultitenantConfiguration(DataSourceProperties properties, MultiTenantRboProperties rbosProperties) {
		this.properties = properties;
		this.rbosProperties = rbosProperties;
	}

	@Bean
	public DataSource dataSource() {
		MultitenantDataSource dataSource = new MultitenantDataSource();
		dataSource.setDefaultTargetDataSource(defaultDataSource());
		dataSource.setTargetDataSources(targetDatSources());
		dataSource.afterPropertiesSet();
		return dataSource;
	}

	private Map<Object,Object> targetDatSources() {
		Map<Object,Object> result = new HashMap<>();
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader());
		for (RboProperty rboProperty : rbosProperties.getRbo()) {
			dataSourceBuilder
					.url(rboProperty.getUrl())
					.password(properties.getPassword())
					.username(properties.getUsername());
			result.put(rboProperty.getCode(), dataSourceBuilder.build());
		}
		return result;
	}

	private DataSource defaultDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader())
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword());
		if (properties.getType() != null) {
			dataSourceBuilder.type(properties.getType());
		}
		return dataSourceBuilder.build();
	}

}
