package com.shoppingapp.business.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.joda.time.LocalDate;


public class CreateProvisionForPurchaseCostCommand {
    @TargetAggregateIdentifier
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseOfGoods;

    public CreateProvisionForPurchaseCostCommand(String id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForPurchaseOfGoods = provisionForPurchaseOfGoods;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForPurchaseOfGoods() {
        return provisionForPurchaseOfGoods;
    }

}
