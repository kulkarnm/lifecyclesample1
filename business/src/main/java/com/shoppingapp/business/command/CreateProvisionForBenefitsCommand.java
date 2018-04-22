package com.shoppingapp.business.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

public class CreateProvisionForBenefitsCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double provisionForBenefits;

    public CreateProvisionForBenefitsCommand(String id, LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForBenefits = provisionForBenefits;
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

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }
}
