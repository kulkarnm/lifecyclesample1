package com.shoppingapp.product.command.handler;

import com.shoppingapp.product.command.SetProductPricingConfigurationCommand;
import com.shoppingapp.product.domain.Product;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetProductPricingConfigurationCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public SetProductPricingConfigurationCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(SetProductPricingConfigurationCommand command) {
        final Aggregate<Product> product = repository.load(command.getProductId());
        product.execute(aggregateRoot -> {
                aggregateRoot.setProductPricingConfiguration(command);
        });
    }
}
