package com.shoppingapp.product.web.request;

import com.shoppingapp.common.vo.Period;
import com.shoppingapp.common.vo.PricingOptions;
import com.shoppingapp.common.vo.PricingStrategyType;
import com.shoppingapp.product.vo.CostHeaderType;

public class ProductPricingConfigurationRequest {
    private int actualsAggregationPeriodForTargetForecast;
    private double targetChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private PricingOptions pricingOptions;
    private PricingStrategyType pricingStrategyType;
    private Period demandCurvePeriod;
    private double tentativePercentageChangeInProductDemand;
    private CostHeaderType[] costHeaderTypes;
    private double contingencyStockPercentage;

    public double getTargetChangeThresholdForPriceChange() {
        return targetChangeThresholdForPriceChange;
    }

    public void setTargetChangeThresholdForPriceChange(double targetChangeThresholdForPriceChange) {
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
    }

    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public void setCrossPriceElasticityConsidered(boolean crossPriceElasticityConsidered) {
        isCrossPriceElasticityConsidered = crossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }

    public void setAdvertisingExpensesConsidered(boolean advertisingExpensesConsidered) {
        isAdvertisingExpensesConsidered = advertisingExpensesConsidered;
    }

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public void setActualsAggregationPeriodForTargetForecast(int actualsAggregationPeriodForTargetForecast) {
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public double getTentativePercentageChangeInProductDemand() {
        return tentativePercentageChangeInProductDemand;
    }

    public void setTentativePercentageChangeInProductDemand(double tentativePercentageChangeInProductDemand) {
        this.tentativePercentageChangeInProductDemand = tentativePercentageChangeInProductDemand;
    }

    public CostHeaderType[] getCostHeaderTypes() {
        return costHeaderTypes;
    }

    public void setCostHeaderTypes(CostHeaderType[] costHeaderTypes) {
        this.costHeaderTypes = costHeaderTypes;
    }

    public double getContingencyStockPercentage() {
        return contingencyStockPercentage;
    }

    public void setContingencyStockPercentage(double contingencyStockPercentage) {
        this.contingencyStockPercentage = contingencyStockPercentage;
    }
}
