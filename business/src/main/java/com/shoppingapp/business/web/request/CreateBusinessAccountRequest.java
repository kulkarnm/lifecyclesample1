package com.shoppingapp.business.web.request;

import org.joda.time.LocalDate;

public class CreateBusinessAccountRequest {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateOfProvision;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDateOfProvision() {
        return dateOfProvision;
    }

    public void setDateOfProvision(LocalDate dateOfProvision) {
        this.dateOfProvision = dateOfProvision;
    }
}
