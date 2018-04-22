package com.shoppingapp.product.command.handler;

import com.shoppingapp.product.command.RegisterProductCommand;
import com.shoppingapp.product.domain.Product;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RegisterProductCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductCommandHandler.class);
    private Repository<Product> repository;

    @Autowired
    public RegisterProductCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(RegisterProductCommand command) throws Exception {
        repository.newInstance(() -> {
            return new Product(
                    command.getProductId(),
                    command.getProductName(),
                    command.getCategoryId(),
                    command.getSubCategoryId(),
                    command.getQuantity(),
                    command.getQuantityUnit(),
                    command.getSubstitutes(),
                    command.getComplements(),
                    command.getProductPricingCategory(),
                    command.getPurchasePrice(),
                    command.getMRP(),
                    command.getCreationDate()
            );
        });
        LOGGER.info("Product added to write repository with Name: " + command.getProductName() + " category:" + command.getCategoryId() + " subcategory: " + command.getSubCategoryId() + " on date: " + LocalDate.now());
    }
}
