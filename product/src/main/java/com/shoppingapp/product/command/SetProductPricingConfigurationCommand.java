package com.shoppingapp.product.command;

import com.shoppingapp.common.vo.Period;
import com.shoppingapp.common.vo.PricingOptions;
import com.shoppingapp.common.vo.PricingStrategyType;
import com.shoppingapp.product.vo.CostHeaderType;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;


public class SetProductPricingConfigurationCommand {
    @TargetAggregateIdentifier
    private String productId;
    private PricingOptions pricingOptions;
    private PricingStrategyType pricingStrategyType;
    private List<CostHeaderType> costHeaderTypes;
    private double contingencyStockPercentage;

    public SetProductPricingConfigurationCommand(String productId, int actualsAggregationPeriodForTargetForecast, double targetChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered, PricingOptions pricingOptions, PricingStrategyType pricingStrategyType, Period demandCurvePeriod,double tentativePercentageChangeInProductDemand,List<CostHeaderType> costHeaderTypes,double contingencyStockPercentage) {
        this.productId = productId;
        this.pricingOptions = pricingOptions;
        this.pricingStrategyType = pricingStrategyType;
        this.costHeaderTypes=costHeaderTypes;
        this.contingencyStockPercentage=contingencyStockPercentage;
    }

    public String getProductId() {
        return productId;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public List<CostHeaderType> getCostHeaderTypes() {
        return costHeaderTypes;
    }

    public double getContingencyStockPercentage() {
        return contingencyStockPercentage;
    }
}
