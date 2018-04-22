package com.shoppingapp.business.command.handler;

import com.shoppingapp.business.command.CreateProvisionForTaxesCommand;
import com.shoppingapp.business.domain.BusinessAccount;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProvisionForTaxesCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForTaxesCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForTaxesCommand command) {
        String id= command.getId();
        try {
            Aggregate<BusinessAccount> businessAccount = repository.load(id);
            businessAccount.execute(aggregateRoot->{
                aggregateRoot.registerProvisionForTaxes(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForOthers());
            });
        } catch (AggregateNotFoundException e) {
        }
    }

}
