package com.shoppingapp.business.domain;

import com.shoppingapp.business.vo.BudgetSettingOptions;
import com.shoppingapp.common.vo.FiscalYearConfig;

public class BusinessAccountConfiguration {
    private String businessAccountId;
    private BudgetSettingOptions budgetSettingOptions;
    private FiscalYearConfig fiscalYearConfig;

    public BusinessAccountConfiguration(String businessAccountId){this.businessAccountId=businessAccountId;}

    public BudgetSettingOptions getBudgetSettingOptions() {
        return budgetSettingOptions;
    }

    public void setBudgetSettingOptions(BudgetSettingOptions budgetSettingOptions) {
        this.budgetSettingOptions = budgetSettingOptions;
    }

    public FiscalYearConfig getFiscalYearConfig() {
        return fiscalYearConfig;
    }

    public void setFiscalYearConfig(FiscalYearConfig fiscalYearConfig) {
        this.fiscalYearConfig = fiscalYearConfig;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

}
