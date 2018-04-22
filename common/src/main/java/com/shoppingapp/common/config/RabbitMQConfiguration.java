package com.shoppingapp.common.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.axonframework.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;


@Configuration
public class RabbitMQConfiguration {

    @Resource(name = "types")
    Map<String, String> types;

    @Bean
    public RabbitAdmin rabbitAdmin(CachingConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public String exchange(@Value("${axon.eventBus.exchangeName}") String exchangeName,
                             @Value("${shoppingapp.rabbitmq.exchange.host}") String host,
                             @Value("${shoppingapp.rabbitmq.queue}") String queueName) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        ExecutorService es = Executors.newFixedThreadPool(40);
        Connection conn = connectionFactory.newConnection(es);
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(exchangeName, "topic", true);

        channel.queueDeclare(queueName, true, false, false, null);
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + "@@@@@@Types: " + types
                + "\n\t\t\t\t******************************\n");
        for (String bindingKey : types.keySet()) {
            channel.queueBind(queueName, exchangeName, bindingKey);
        }
        return exchangeName;
    }

    @Bean
    public SpringAMQPPublisher springAMQPPublisher(String exchange, EventStore eventStore,
                                               Serializer serializer, CachingConnectionFactory connectionFactory,
                                               RoutingKeyResolver routingKeyResolver, AMQPMessageConverter messageConverter) {
        SpringAMQPPublisher springAMQPTerminal = new SpringAMQPPublisher(eventStore);
        springAMQPTerminal.setExchangeName(exchange);
        springAMQPTerminal.setSerializer(serializer);
        springAMQPTerminal.setRoutingKeyResolver(routingKeyResolver);
        springAMQPTerminal.setConnectionFactory(connectionFactory);
        springAMQPTerminal.setMessageConverter(messageConverter);
        springAMQPTerminal.start();
        return springAMQPTerminal;
    }

    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
        SpringAMQPMessageSource springAMQPMessageSource = new SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = "${shoppingapp.rabbitmq.queue}", containerFactory = "rabbitListenerContainerFactory")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                super.onMessage(message, channel);
            }
        };
        return springAMQPMessageSource;
    }

    @Bean
    public RoutingKeyResolver routingKeyResolver() {
        return new EventTypeRoutingKeyResolver();
    }

    @Bean
    public AMQPMessageConverter messageConverter(Serializer serializer, RoutingKeyResolver routingKeyResolver) {
        return new DefaultAMQPMessageConverter(serializer, routingKeyResolver, true);
    }

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(20);
        factory.setMaxConcurrentConsumers(30);
        return factory;
    }
}
