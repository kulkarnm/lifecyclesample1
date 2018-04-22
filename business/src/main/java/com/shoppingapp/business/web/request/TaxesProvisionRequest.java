package com.shoppingapp.business.web.request;

import org.joda.time.LocalDate;

public class TaxesProvisionRequest {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForTaxes;

    public TaxesProvisionRequest(String merchantId,LocalDate startDate, LocalDate endDate, double provisionForTaxes) {
        this.merchantId=merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForTaxes = provisionForTaxes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForTaxes() {
        return provisionForTaxes;
    }

    public String getMerchantId() {
        return merchantId;
    }
}
