package com.shoppingapp.business.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.joda.time.LocalDate;


public class CreateProvisionForTaxesCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double provisionForOthers;

    public CreateProvisionForTaxesCommand(String id, LocalDate startDate, LocalDate endDate, double provisionForOthers) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForOthers = provisionForOthers;
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

    public double getProvisionForOthers() {
        return provisionForOthers;
    }
}
