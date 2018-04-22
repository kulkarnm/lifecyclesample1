package com.shoppingapp.product.configuration;

import com.mongodb.Mongo;
import com.shoppingapp.common.config.ComponentConfiguration;
import com.shoppingapp.common.generator.DefaultIdGenerator;
import com.shoppingapp.common.idconverter.ProductVersionIdReaderConverter;
import com.shoppingapp.common.idconverter.ProductVersionIdWriterConverter;
import com.shoppingapp.common.idconverter.ProductwiseTaggedPriceVersionIdReaderConverter;
import com.shoppingapp.common.idconverter.ProductwiseTaggedPriceVersionIdWriterConverter;
import com.shoppingapp.product.domain.Product;
import com.shoppingapp.product.event.ProvisionCreatedEvent;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("com.shoppingapp")
public class Axon extends ComponentConfiguration {

    @Bean
    @Scope("prototype")
    public Product product() {
        return new Product();
    }

    @Bean
    public Repository<Product> productRepository(EventStore eventStore) {

        EventSourcingRepository<Product> repository = new EventSourcingRepository<>(Product.class,
                eventStore);

        return repository;
    }

    @Bean
    public AggregateFactory<Product> productAggregateFactory() {
        SpringPrototypeAggregateFactory<Product> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("product");

        return aggregateFactory;
    }
    @Bean
    DefaultIdGenerator defaultIdGenerator(){
        return new DefaultIdGenerator();
    }
    @Bean
    public CustomConversions customConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new ProductVersionIdReaderConverter());
        converters.add(new ProductVersionIdWriterConverter());
        converters.add(new ProductwiseTaggedPriceVersionIdReaderConverter());
        converters.add(new ProductwiseTaggedPriceVersionIdWriterConverter());
        return new CustomConversions(converters);
    }
    @Bean
    public MappingMongoConverter mappingMongoConverter(Mongo mongo, MongoDbFactory mongoDbFactory) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions());
        return converter;
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.shoppingapp.product.event.*", "");
            put("com.shoppingapp.business.event.ProvisionCreatedEvent", ProvisionCreatedEvent.class.getName());
        }};
    }

}