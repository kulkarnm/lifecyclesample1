package com.shoppingapp.business.web.request;

import org.joda.time.LocalDate;

public class PurchaseCostProvisionRequest {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseOfGoods;

    public String getMerchantId() {
        return merchantId;
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
