package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.product.repository.ProductBusinessAccountViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(mongoTemplateRef="productMongoTemplate",basePackageClasses = {ProductBusinessAccountViewRepository.class,
com.verifier.domains.product.repository.CategoryDetailsViewRepository.class,
        com.verifier.domains.product.repository.ProductActivationStatusViewPagingRepository.class,
        com.verifier.domains.product.repository.ProductActivationStatusViewRepository.class,
        com.verifier.domains.product.repository.ProductConfigurationViewRepository.class,
        com.verifier.domains.product.repository.ProductViewPagingRepository.class,
        com.verifier.domains.product.repository.ProductViewRepository.class,
        com.verifier.domains.product.repository.TaggedPriceVersionsViewRepository.class})
public class ProductMongoConfiguration {
    @Bean
    @Qualifier("productMongoTemplate")
    public MongoTemplate productMongoTemplate(@Qualifier("productMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside ProductMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }

    @Bean
    @Qualifier("ProductMongo")
    public MongoClient mongo(@Qualifier("ProductMongoClientURI") MongoClientURI mongoClientURI) {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("ProductMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.product.host}") String host, @Value("${view.db.product.port}") int port,
                                         @Value("${view.db.product.name}") String dbName) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    @Qualifier("productMongoDbFactory")
    public MongoDbFactory productMongoDbFactory(@Value("${view.db.product.host}") String host, @Value("${view.db.product.port}") int port,
                                         @Value("${view.db.product.name}") String dbName) {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
