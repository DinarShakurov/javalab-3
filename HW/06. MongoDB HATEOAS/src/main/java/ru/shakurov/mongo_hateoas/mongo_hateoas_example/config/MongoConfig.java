package ru.shakurov.mongo_hateoas.mongo_hateoas_example.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories.spring")
@PropertySource("classpath:db.properties")
public class MongoConfig {

    @Value("${database.name}")
    private String dbName;

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(), dbName);
    }
}
