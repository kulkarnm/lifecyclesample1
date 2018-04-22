package com.shoppingapp.business.command;

import org.joda.time.LocalDate;

public class CreateBusinessAccountCommand {
    private String id;
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateOfProvision;

    public CreateBusinessAccountCommand(String id, String merchantId, LocalDate startDate, LocalDate endDate, LocalDate dateOfProvision) {
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
