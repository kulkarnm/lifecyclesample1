package com.shoppingapp.business.event;

import com.shoppingapp.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;

import java.util.List;

public class ProvisionForTaxesRegisteredEvent {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForTaxes;
    private List<ProvisionSegment> provisionDistribution;


    public ProvisionForTaxesRegisteredEvent(String id, LocalDate startDate, LocalDate endDate, double provisionForTaxes,List<ProvisionSegment> provisionDistribution) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForTaxes=provisionForTaxes;
        this.provisionDistribution=provisionDistribution;

    }
    public ProvisionForTaxesRegisteredEvent(){}
    public String getId() {
        return id;
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

    public List<ProvisionSegment> getProvisionDistribution() {
        return provisionDistribution;
    }
}
