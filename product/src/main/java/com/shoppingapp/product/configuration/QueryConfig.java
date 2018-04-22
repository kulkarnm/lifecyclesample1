/*
 * Copyright (c) 2010-2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shoppingapp.product.configuration;

import com.shoppingapp.product.event.listener.ProductPricingConfigurationSetEventListener;
import com.shoppingapp.product.event.listener.ProductRegisteredEventListener;
import com.shoppingapp.product.event.listener.ProvisionCreatedEventListener;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.axonframework.eventhandling.SimpleEventHandlerInvoker;
import org.axonframework.eventhandling.SubscribingEventProcessor;
import org.axonframework.eventhandling.async.AsynchronousEventProcessingStrategy;
import org.axonframework.eventhandling.async.SequentialPerAggregatePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

@Configuration
public class QueryConfig {

    @Autowired
    private SpringAMQPMessageSource springAMQPMessageSource;

    @Autowired private ProductRegisteredEventListener productRegisteredEventListener;
    @Autowired private ProvisionCreatedEventListener provisionCreatedEventListener;
    @Autowired ProductPricingConfigurationSetEventListener productPricingConfigurationSetEventListener;

    @Bean
    public EventProcessor queryEventProcessor() {
        SubscribingEventProcessor eventProcessor = new SubscribingEventProcessor("queryEventProcessor",
                                                                                 new SimpleEventHandlerInvoker(
                                                                                         productRegisteredEventListener,
                                                                                         provisionCreatedEventListener,
                                                                                         productPricingConfigurationSetEventListener),
                                                                                 springAMQPMessageSource,
                new AsynchronousEventProcessingStrategy(new ConcurrentTaskExecutor(), new SequentialPerAggregatePolicy()),
                PropagatingErrorHandler.instance());
        eventProcessor.start();
        return eventProcessor;
    }
}
