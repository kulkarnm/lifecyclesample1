package com.shoppingapp.business.command.handler;

import com.shoppingapp.business.command.CreateProvisionForBenefitsCommand;
import com.shoppingapp.business.domain.BusinessAccount;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProvisionForBenefitsCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForBenefitsCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForBenefitsCommand command) {
        String id= command.getId();
        try {
            Aggregate<BusinessAccount> businessAccount = repository.load(id);
            businessAccount.execute(aggregateRoot->{
                aggregateRoot.registerProvisionForBenefits(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForBenefits());
            });
        } catch (AggregateNotFoundException e) {
        }

    }
}
