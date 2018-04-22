package com.shoppingapp.business.configuration;

import com.shoppingapp.business.domain.BusinessAccount;
import com.shoppingapp.business.event.ProductActivatedEvent;
import com.shoppingapp.business.event.ProductRegisteredEvent;
import com.shoppingapp.common.config.ComponentConfiguration;
import com.shoppingapp.common.generator.DefaultIdGenerator;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.shoppingapp")
public class Axon extends ComponentConfiguration {

    @Bean
    @Scope("prototype")
    public BusinessAccount businessAccount() {
        return new BusinessAccount();
    }

    @Bean
    public Repository<BusinessAccount> businessAccountRepository(EventStore eventStore) {
        EventSourcingRepository<BusinessAccount> repository = new EventSourcingRepository<>(BusinessAccount.class,
                eventStore);
        return repository;
    }

    @Bean
    public AggregateFactory<BusinessAccount> productAggregateFactory() {
        SpringPrototypeAggregateFactory<BusinessAccount> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("businessAccount");

        return aggregateFactory;
    }
    @Bean
    DefaultIdGenerator defaultIdGenerator(){
        return new DefaultIdGenerator();
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.shoppingapp.business.event.*", "");
            put("com.shoppingapp.product.event.ProductRegisteredEvent", ProductRegisteredEvent.class.getName());
            put("com.shoppingapp.product.event.ProductActivatedEvent", ProductActivatedEvent.class.getName());
        }};
    }
}
