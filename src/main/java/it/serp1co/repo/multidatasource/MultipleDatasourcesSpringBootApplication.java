package it.serp1co.repo.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultipleDatasourcesSpringBootApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MultipleDatasourcesSpringBootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatasourcesSpringBootApplication.class, args);
	}

}
