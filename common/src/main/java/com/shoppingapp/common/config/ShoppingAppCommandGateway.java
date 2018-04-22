package com.shoppingapp.common.config;

import org.axonframework.commandhandling.gateway.CommandGateway;

public interface ShoppingAppCommandGateway extends CommandGateway {

    void executeAsync(Object command) throws Exception;
}
