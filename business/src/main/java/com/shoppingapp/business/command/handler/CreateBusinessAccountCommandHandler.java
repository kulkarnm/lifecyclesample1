package com.shoppingapp.business.command.handler;

import com.shoppingapp.business.command.CreateBusinessAccountCommand;
import com.shoppingapp.business.domain.BusinessAccount;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessAccountCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateBusinessAccountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateBusinessAccountCommand command) throws Exception{
        repository.newInstance(()->{
            return new BusinessAccount(command.getId(),command.getMerchantId(),command.getStartDate(),command.getEndDate(),command.getDateOfProvision());
        });
    }
}
