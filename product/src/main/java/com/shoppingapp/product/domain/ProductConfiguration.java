package com.shoppingapp.product.domain;

import com.shoppingapp.common.vo.Period;
import com.shoppingapp.common.vo.PeriodUnit;
import com.shoppingapp.common.vo.PricingOptions;
import com.shoppingapp.common.vo.PricingStrategyType;
import com.shoppingapp.product.vo.CostHeaderType;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class ProductConfiguration {
    private String productId;
    private PricingStrategyType pricingStrategyType;
    private PricingOptions pricingOptions;
    private List<CostHeaderType> costHeaderTypes;
    private double contingencyStockPercentage;
    public ProductConfiguration(String productId, PricingOptions pricingOptions, PricingStrategyType pricingStrategyType,List<CostHeaderType> costHeaderTypes, double contingencyStockPercentage) {
        this.productId = productId;
        if (null == pricingOptions) {
            this.pricingOptions = PricingOptions.ACCEPT_AUTOMATED_PRICE_GENERATION;
        } else {
            this.pricingOptions = pricingOptions;
        }
        if (null == pricingStrategyType) {
            this.pricingStrategyType = PricingStrategyType.DEFAULT_PRICING_STRATEGY;
        } else {
            this.pricingStrategyType = pricingStrategyType;
        }
        if (null == costHeaderTypes) {
            this.costHeaderTypes = new ArrayList<>();
            this.costHeaderTypes.add(CostHeaderType.FIXED_EXPENSE_PER_UNIT);
            this.costHeaderTypes.add(CostHeaderType.VARIABLE_EXPENSE_PER_UNIT);
            this.costHeaderTypes.add(CostHeaderType.PURCHASE_PRICE_PER_UNIT);
            this.costHeaderTypes.add(CostHeaderType.MERCHANT_MARGIN_PER_UNIT);
            this.costHeaderTypes.add(CostHeaderType.OTHERS_PER_UNIT);
        } else {
            this.costHeaderTypes = costHeaderTypes;
        }
        if (0 == contingencyStockPercentage) {
            this.contingencyStockPercentage = 0.1;
        } else {
            this.contingencyStockPercentage = contingencyStockPercentage;
        }
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public String getProductId() {
        return productId;
    }


    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public List<CostHeaderType> getCostHeaderTypes() {
        return costHeaderTypes;
    }

    public void setCostHeaderTypes(List<CostHeaderType> costHeaderTypes) {
        this.costHeaderTypes = costHeaderTypes;
    }

    public double getContingencyStockPercentage() {
        return contingencyStockPercentage;
    }

    public void setContingencyStockPercentage(double contingencyStockPercentage) {
        this.contingencyStockPercentage = contingencyStockPercentage;
    }

}
