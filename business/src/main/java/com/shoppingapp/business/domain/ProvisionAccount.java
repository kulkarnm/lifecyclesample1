package com.shoppingapp.business.domain;

import com.shoppingapp.business.vo.ProvisionCalendar;
import com.shoppingapp.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


public class ProvisionAccount {
    private double provisionAmount;
    private double targetProvisionAmount;
    private ProvisionCalendar provisionCalendar;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProvisionAccount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionCalendar = new ProvisionCalendar(startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setTargetProvisionAmount(double targetProvisionAmount) {
        this.targetProvisionAmount = targetProvisionAmount;
    }

    public double getTargetProvisionAmount() {
        return targetProvisionAmount;
    }

    public ProvisionCalendar getProvisionCalendar() {
        return provisionCalendar;
    }


    public void registerProvision(String id, LocalDate startDate, LocalDate endDate, double provision, List<ProvisionSegment> provisionDistributionCalendar) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionAmount = provision;
        this.provisionCalendar = new ProvisionCalendar(startDate, endDate, provisionDistributionCalendar);
    }

    public List<ProvisionSegment> distributeProvision(String id, LocalDate startDate, LocalDate endDate, double provision) {
        return provisionCalendar.distributeProvisionAcrossYear(provision, startDate, endDate);
    }

}
