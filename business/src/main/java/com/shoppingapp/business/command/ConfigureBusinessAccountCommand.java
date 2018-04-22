package com.shoppingapp.business.command;

import com.shoppingapp.business.vo.BudgetSettingOptions;
import com.shoppingapp.common.vo.FiscalYearConfig;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class ConfigureBusinessAccountCommand {
    @TargetAggregateIdentifier
    private String id;
    private BudgetSettingOptions budgetSettingOptions;
    private FiscalYearConfig fiscalYearConfig;

    public ConfigureBusinessAccountCommand(String id, BudgetSettingOptions budgetSettingOptions, FiscalYearConfig fiscalYearConfig) {
        this.id=id;
        this.budgetSettingOptions = budgetSettingOptions;
        this.fiscalYearConfig=fiscalYearConfig;
    }

    public String getId() {
        return id;
    }

    public BudgetSettingOptions getBudgetSettingOptions() {
        return budgetSettingOptions;
    }

    public FiscalYearConfig getFiscalYearConfig() {
        return fiscalYearConfig;
    }
}
