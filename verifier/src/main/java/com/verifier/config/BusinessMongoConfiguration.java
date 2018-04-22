package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.business.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(mongoTemplateRef="businessMongoTemplate",  basePackageClasses = {BusinessBusinessAccountViewRepository.class,
        BusinessBenefitAccountTransactionsViewRepository.class,
        BusinessBenefitAccountViewRepository.class,
        BusinessBusinessAccountConfigurationViewRepository.class,
        BusinessProductViewRepository.class,
        BusinessPurchaseCostAccountTransactionsViewRepository.class,
        BusinessPurchaseCostAccountViewRepository.class,
        BusinessTaxesAccountTransactionsViewRepository.class,
        BusinessTaxesAccountViewRepository.class,
})
public class BusinessMongoConfiguration {
    @Bean
    @Qualifier("businessMongoTemplate")
    @Primary
    public MongoTemplate businessMongoTemplate(@Qualifier("businessMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside BusinessMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate businessMongoTemplate = new MongoTemplate(factory);
        return businessMongoTemplate;
    }

    @Bean
    @Qualifier("BusinessMongo")
    public MongoClient businessMongo(@Qualifier("BusinessMongoClientURI") MongoClientURI mongoClientURI) {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("BusinessMongoClientURI")
    public MongoClientURI businessMongoClientURI(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
                                         @Value("${view.db.business.name}") String dbName) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    @Primary
    @Qualifier("businessMongoDbFactory")
    public MongoDbFactory businessMongoDbFactory(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
                                         @Value("${view.db.business.name}") String dbName) {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
