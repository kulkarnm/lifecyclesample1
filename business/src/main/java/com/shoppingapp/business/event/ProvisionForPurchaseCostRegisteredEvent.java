package com.shoppingapp.business.event;

import com.shoppingapp.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;

import java.util.List;

public class ProvisionForPurchaseCostRegisteredEvent {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseOfGoods;
    private List<ProvisionSegment> provisionDistribution;
    public ProvisionForPurchaseCostRegisteredEvent(String Id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods,List<ProvisionSegment> provisionDistribution) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForPurchaseOfGoods=provisionForPurchaseOfGoods;
        this.provisionDistribution=provisionDistribution;
    }
    public ProvisionForPurchaseCostRegisteredEvent(){}
    public String getId() {
        return id;
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

    public List<ProvisionSegment> getProvisionDistribution() {
        return provisionDistribution;
    }
}
