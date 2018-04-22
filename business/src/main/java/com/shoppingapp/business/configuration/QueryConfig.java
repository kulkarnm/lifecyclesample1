
package com.shoppingapp.business.configuration;

import com.shoppingapp.business.event.listener.*;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.SimpleEventHandlerInvoker;
import org.axonframework.eventhandling.SubscribingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryConfig {

    @Autowired
    private SpringAMQPMessageSource springAMQPMessageSource;

    @Autowired private BusinessAccountConfigurationSetEventListener businessAccountConfigurationSetEventListener;
    @Autowired private BusinessAccountCreatedEventListener businessAccountCreatedEventListener;
    @Autowired private ProductActivatedEventListener productActivatedEventListener;
    @Autowired private ProductRegisteredEventListener productRegisteredEventListener;
    @Autowired private ProvisionForBenefitsRegisteredEventListener provisionForBenefitsRegisteredEventListener;
    @Autowired private ProvisionForPurchaseCostRegisteredEventListener provisionForPurchaseCostRegisteredEventListener;
    @Autowired private ProvisionForTaxesRegisteredEventListener provisionForTaxesRegisteredEventListener;

    @Bean
    public EventProcessor queryEventProcessor() {
        SubscribingEventProcessor eventProcessor = new SubscribingEventProcessor("businessEventProcessor",
                                 new SimpleEventHandlerInvoker(
                                         businessAccountConfigurationSetEventListener,
                                         businessAccountCreatedEventListener,
                                         productActivatedEventListener,
                                         productRegisteredEventListener,
                                         provisionForBenefitsRegisteredEventListener,
                                         provisionForPurchaseCostRegisteredEventListener,
                                         provisionForTaxesRegisteredEventListener),
                                 springAMQPMessageSource);
        eventProcessor.start();


        return eventProcessor;
    }
}
