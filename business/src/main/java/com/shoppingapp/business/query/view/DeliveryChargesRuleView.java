package com.shoppingapp.business.query.view;

import com.shoppingapp.common.vo.QuantityUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;

@Document(collection = "DeliveryChargesRuleView")
public class DeliveryChargesRuleView {
    @Id
    private String ruleId;
    private String ruleHeader;
    private double ruleMinimum;
    private double ruleMaximum;
    private QuantityUnit ruleUnit;
    private double applicableValue;
    @Autowired
    private Currency currency;

    public DeliveryChargesRuleView(String ruleId, String ruleHeader, double ruleMinimum, double ruleMaximum, QuantityUnit ruleUnit, double applicableValue) {
        this.ruleId = ruleId;
        this.ruleHeader = ruleHeader;
        this.ruleMinimum = ruleMinimum;
        this.ruleMaximum = ruleMaximum;
        this.ruleUnit = ruleUnit;
        this.applicableValue = applicableValue;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleHeader() {
        return ruleHeader;
    }

    public void setRuleHeader(String ruleHeader) {
        this.ruleHeader = ruleHeader;
    }

    public double getRuleMinimum() {
        return ruleMinimum;
    }

    public void setRuleMinimum(double ruleMinimum) {
        this.ruleMinimum = ruleMinimum;
    }

    public double getRuleMaximum() {
        return ruleMaximum;
    }

    public void setRuleMaximum(double ruleMaximum) {
        this.ruleMaximum = ruleMaximum;
    }

    public QuantityUnit getRuleUnit() {
        return ruleUnit;
    }

    public void setRuleUnit(QuantityUnit ruleUnit) {
        this.ruleUnit = ruleUnit;
    }

    public double getApplicableValue() {
        return applicableValue;
    }

    public void setApplicableValue(double applicableValue) {
        this.applicableValue = applicableValue;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
