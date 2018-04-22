package com.shoppingapp.business.event;

import com.shoppingapp.business.vo.BudgetSettingOptions;
import com.shoppingapp.common.vo.FiscalYearConfig;

public class BusinessAccountConfigurationSetEvent {
    private String id;
    private BudgetSettingOptions budgetSettingOptions;
    private FiscalYearConfig fiscalYearConfig;
    public BusinessAccountConfigurationSetEvent(String id, BudgetSettingOptions budgetSettingOptions, FiscalYearConfig fiscalYearConfig) {
        this.id=id;
        this.budgetSettingOptions = budgetSettingOptions;
        this.fiscalYearConfig=fiscalYearConfig;
    }
    public BusinessAccountConfigurationSetEvent(){}
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
