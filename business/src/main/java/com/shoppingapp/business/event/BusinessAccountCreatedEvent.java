package com.shoppingapp.business.event;

import org.joda.time.LocalDate;

public class BusinessAccountCreatedEvent {
    private String id;
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateOfProvision;

    public BusinessAccountCreatedEvent(){}
    public BusinessAccountCreatedEvent(String id, String merchantId, LocalDate startDate, LocalDate endDate, LocalDate dateOfProvision) {
        this.id = id;
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateOfProvision = dateOfProvision;
    }

    public String getId() {
        return id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getDateOfProvision() {
        return dateOfProvision;
    }
}
