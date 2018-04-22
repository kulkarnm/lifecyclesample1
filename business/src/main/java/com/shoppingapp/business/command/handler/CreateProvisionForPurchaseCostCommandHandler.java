package com.shoppingapp.business.command.handler;

import com.shoppingapp.business.command.CreateProvisionForPurchaseCostCommand;
import com.shoppingapp.business.domain.BusinessAccount;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProvisionForPurchaseCostCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForPurchaseCostCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForPurchaseCostCommand command) {
        String id= command.getId();
        try {
            Aggregate<BusinessAccount> businessAccount = repository.load(id);
            businessAccount.execute(aggregateRoot->{
                aggregateRoot.registerProvisionForPurchaseCost(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForPurchaseOfGoods());
            });
        } catch (AggregateNotFoundException e) {
        }

    }

}
