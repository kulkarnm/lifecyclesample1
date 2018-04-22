package com.shoppingapp.product.event;

import org.joda.time.LocalDateTime;

public class ProvisionCreatedEvent {
    private Integer businessAccountId;
    private double provisionForPurchaseCost;
    private double provisionForBenefits;
    private double provisionForTaxes;
    private LocalDateTime provisionDate;

    public ProvisionCreatedEvent() {
    }

    public ProvisionCreatedEvent(Integer businessAccountId,
                                 double provisionForPurchaseCost,
                                 double provisionForBenefits,
                                 double provisionForTaxes,
                                 LocalDateTime provisionDate) {
        this.businessAccountId = businessAccountId;
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionForTaxes = provisionForTaxes;
        this.provisionDate = provisionDate;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(Integer businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public void setProvisionForPurchaseCost(double provisionForPurchaseCost) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public LocalDateTime getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionDate(LocalDateTime provisionDate) {
        this.provisionDate = provisionDate;
    }

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public void setProvisionForBenefits(double provisionForBenefits) {
        this.provisionForBenefits = provisionForBenefits;
    }

    public double getProvisionForTaxes() {
        return provisionForTaxes;
    }

    public void setProvisionForTaxes(double provisionForTaxes) {
        this.provisionForTaxes = provisionForTaxes;
    }

}
