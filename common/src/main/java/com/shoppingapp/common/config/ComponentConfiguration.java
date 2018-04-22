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

package com.shoppingapp.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.shoppingapp.common.generator.DefaultIdGenerator;
import com.shoppingapp.common.generator.IdGenerator;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.serialization.AnnotationRevisionResolver;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.spring.config.CommandHandlerSubscriber;
import org.axonframework.spring.config.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.*;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Import({EventStoreConfig.class})
public class ComponentConfiguration {

    private static final String AXON_MESSAGE_TYPE_PATTERN = "headers['axon-message-type']=='%s'";

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        List<BeanValidationInterceptor<CommandMessage<?>>> beanValidationInterceptors =
                Arrays.asList(new BeanValidationInterceptor<>());

        return commandBus;
    }

    @Bean
    public CommandGatewayFactoryBean commandGateway(CommandBus commandBus) {
        RetryScheduler retryScheduler = new IntervalRetryScheduler(newScheduledThreadPool(1), 100, 3);
        CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean = new CommandGatewayFactoryBean<>();
        commandGatewayFactoryBean.setGatewayInterface(CommandGateway.class);
        commandGatewayFactoryBean.setCommandBus(commandBus);
        commandGatewayFactoryBean.setRetryScheduler(retryScheduler);
        //commandGatewayFactoryBean.setCommandDispatchInterceptors(new CommandLoggingInterceptor("commandlogging"));
        return commandGatewayFactoryBean;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        return new AnnotationCommandHandlerBeanPostProcessor();
    }

    @Bean
    public CommandHandlerSubscriber commandHandlerSubscriber() {
        return new CommandHandlerSubscriber();
    }

    @Bean
    public SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean
    public EventBus eventBus () {
        return new SimpleEventBus();
    }

    protected Map<String, String> types() {
        return new HashMap<>();
    }

    @Bean
    public Locale locale (@Value("${shoppingapp.locale}") String locale) {
        return new Locale(locale);
    }

    @Bean
    public IdGenerator generator() {
        return new DefaultIdGenerator();
    }

    @Bean
    public Serializer serializer(ObjectMapper objectMapper) {
        JacksonSerializer serializer = new AxonJacksonSerializer(
                objectMapper, new AnnotationRevisionResolver(), types()
        );
        SimpleModule simpleModule = new SimpleModule("Axon");
        serializer.getObjectMapper().registerModule(simpleModule);
        serializer.getObjectMapper().registerModule(new JodaModule());

        return serializer;
    }
}