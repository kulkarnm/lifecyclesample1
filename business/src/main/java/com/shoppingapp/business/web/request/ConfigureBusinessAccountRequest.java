package com.shoppingapp.business.web.request;

import com.shoppingapp.business.vo.BudgetSettingOptions;
import org.joda.time.LocalDate;

public class ConfigureBusinessAccountRequest {
    private String merchantId;
    private BudgetSettingOptions budgetSettingOptions;
    private LocalDate startDateOfFinancialYear;
    private LocalDate endDateOfFinancialYear;

    public BudgetSettingOptions getBudgetSettingOptions() {
        return budgetSettingOptions;
    }

    public void setBudgetSettingOptions(BudgetSettingOptions budgetSettingOptions) {
        this.budgetSettingOptions = budgetSettingOptions;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public LocalDate getStartDateOfFinancialYear() {
        return startDateOfFinancialYear;
    }

    public void setStartDateOfFinancialYear(LocalDate startDateOfFinancialYear) {
        this.startDateOfFinancialYear = startDateOfFinancialYear;
    }

    public LocalDate getEndDateOfFinancialYear() {
        return endDateOfFinancialYear;
    }

    public void setEndDateOfFinancialYear(LocalDate endDateOfFinancialYear) {
        this.endDateOfFinancialYear = endDateOfFinancialYear;
    }

}
