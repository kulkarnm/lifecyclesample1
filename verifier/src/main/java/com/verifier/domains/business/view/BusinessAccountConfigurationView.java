package com.verifier.domains.business.view;

import com.shoppingapp.common.vo.FiscalYearConfig;
import com.verifier.domains.business.vo.BudgetSettingOptions;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="BusinessAccountConfigurationView")
public class BusinessAccountConfigurationView {
    private String businessAccountId;
    private BudgetSettingOptions budgetSettingOptions;
    private FiscalYearConfig fiscalYearConfig;

    public BusinessAccountConfigurationView(){}
    public BusinessAccountConfigurationView(String businessAccountId, BudgetSettingOptions budgetSettingOptions, FiscalYearConfig fiscalYearConfig) {
        this.businessAccountId = businessAccountId;
        this.budgetSettingOptions = budgetSettingOptions;
        this.fiscalYearConfig=fiscalYearConfig;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

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

}
