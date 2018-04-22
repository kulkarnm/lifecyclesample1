package com.shoppingapp.business.event;

import com.shoppingapp.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;

import java.util.List;

public class ProvisionForBenefitsRegisteredEvent {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForBenefits;
    private List<ProvisionSegment> distributionCalendar;

    public ProvisionForBenefitsRegisteredEvent(String id, LocalDate startDate, LocalDate endDate, double provisionForBenefits,List<ProvisionSegment> distributionCalendar) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForBenefits=provisionForBenefits;
        this.distributionCalendar=distributionCalendar;
    }
    public ProvisionForBenefitsRegisteredEvent(){}
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

    public List<ProvisionSegment> getDistributionCalendar() {
        return distributionCalendar;
    }
}
