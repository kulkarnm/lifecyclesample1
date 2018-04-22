package com.shoppingapp.business.vo;

import org.joda.time.LocalDate;

public class ProvisionSegment {
    private LocalDate startDate;
    private LocalDate endDate;
    private double expectedAmount;
    private double provisionedAmount;
    public ProvisionSegment(){}
    public ProvisionSegment(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public double getProvisionedAmount() {
        return provisionedAmount;
    }

    public void credit(double additionalAmount){
        provisionedAmount +=additionalAmount;
    }
    public void debit(double amountTobeDeducted){
        provisionedAmount -=amountTobeDeducted;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }
}
