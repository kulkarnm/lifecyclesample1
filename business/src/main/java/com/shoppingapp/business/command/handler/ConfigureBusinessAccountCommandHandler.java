package com.shoppingapp.business.command.handler;

import com.shoppingapp.business.command.ConfigureBusinessAccountCommand;
import com.shoppingapp.business.domain.BusinessAccount;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConfigureBusinessAccountCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public ConfigureBusinessAccountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ConfigureBusinessAccountCommand command){
        Aggregate<BusinessAccount> businessAccount = repository.load(command.getId());
        businessAccount.execute(aggregateRoot->{
            aggregateRoot.configureBusinessAccount(command.getBudgetSettingOptions(),command.getFiscalYearConfig());
        });
    }
}
