package com.verifier.domains.product.view;

import com.shoppingapp.common.vo.Period;
import com.shoppingapp.common.vo.PricingOptions;
import com.shoppingapp.common.vo.PricingStrategyType;
import com.verifier.domains.product.vo.CostHeaderType;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ProductConfigurationView")
public class ProductConfigurationView {
    @Id
    private String productId;
    private PricingStrategyType pricingStrategyType;
    private PricingOptions pricingOptions;
    private List<CostHeaderType> costHeaderTypes;
    private double contingencyStockPercentage;

    public ProductConfigurationView(String productId,PricingStrategyType pricingStrategyType, PricingOptions pricingOptions,List<CostHeaderType> costHeaderTypes, double contingencyStockPercentage) {
        this.productId = productId;
        this.pricingStrategyType = pricingStrategyType;
        this.pricingOptions = pricingOptions;
        this.costHeaderTypes=costHeaderTypes;
        this.contingencyStockPercentage=contingencyStockPercentage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
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
