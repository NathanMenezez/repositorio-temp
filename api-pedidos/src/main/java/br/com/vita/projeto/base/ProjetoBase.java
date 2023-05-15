package br.com.vita.projeto.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { JpaRepositoriesAutoConfiguration.class, MongoAutoConfiguration.class, MongoReactiveAutoConfiguration.class, MongoDataAutoConfiguration.class, EmbeddedMongoAutoConfiguration.class })
@EntityScan(basePackages = {"br.com.vita.projeto.base.model.entities"} )
@EnableJpaRepositories(basePackages = {"br.com.vita.projeto.base.repository"})
@EnableMongoRepositories(basePackages = {"br.com.vita.projeto.base.repository"})
public class ProjetoBase {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBase.class, args);
	}
}
