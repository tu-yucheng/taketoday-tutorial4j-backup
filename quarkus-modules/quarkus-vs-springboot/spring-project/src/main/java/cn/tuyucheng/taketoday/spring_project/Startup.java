package cn.tuyucheng.taketoday.spring_project;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

@SpringBootApplication
@EnableR2dbcRepositories
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ByteArrayResource((""
			+ "DROP TABLE IF EXISTS zipcode;"
			+ "CREATE TABLE zipcode (zip VARCHAR(100) PRIMARY KEY, type VARCHAR(255) NULL, city VARCHAR(255) NULL, state VARCHAR(255) NULL, county VARCHAR(255) NULL, timezone VARCHAR(255) NULL);")
			.getBytes())));

		return initializer;
	}

	@Bean
	ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
		return new R2dbcTransactionManager(connectionFactory);
	}
}