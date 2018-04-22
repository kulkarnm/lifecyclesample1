package com.shoppingapp.common.config;

import org.axonframework.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.eventhandling.EventMessage;

public class EventTypeRoutingKeyResolver implements RoutingKeyResolver {
    @Override
    public String resolveRoutingKey(EventMessage eventMessage) {
        System.out.println(eventMessage.getPayloadType().getName());
        return eventMessage.getPayloadType().getName();
    }
}
