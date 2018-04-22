package com.shoppingapp.product.event;

import com.shoppingapp.common.vo.Period;
import com.shoppingapp.common.vo.PricingOptions;
import com.shoppingapp.common.vo.PricingStrategyType;
import com.shoppingapp.product.vo.CostHeaderType;

import java.util.List;


public class ProductPricingConfigurationSetEvent {
    private String productId;
    private PricingOptions pricingOptions;
    private PricingStrategyType pricingStrategyType;
    private List<CostHeaderType> costHeaderTypes;
    private double contingencyStockPercentage;

    public ProductPricingConfigurationSetEvent(String productId, PricingOptions pricingOptions, PricingStrategyType pricingStrategyType,List<CostHeaderType> costHeaderTypes,double contingencyStockPercentage) {
        this.productId = productId;
        this.pricingOptions = pricingOptions;
        this.pricingStrategyType = pricingStrategyType;
        this.costHeaderTypes=costHeaderTypes;
        this.contingencyStockPercentage=contingencyStockPercentage;
    }

    public ProductPricingConfigurationSetEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public List<CostHeaderType> getCostHeaderTypes() {
        return costHeaderTypes;
    }

    public double getContingencyStockPercentage() {
        return contingencyStockPercentage;
    }
}
