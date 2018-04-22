package com.shoppingapp.business.web.request;

import org.joda.time.LocalDate;

public class BenefitsProvisionRequest {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForBenefits;

    public BenefitsProvisionRequest(String merchantId,LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.merchantId=merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForBenefits = provisionForBenefits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public String getMerchantId() {
        return merchantId;
    }
}
