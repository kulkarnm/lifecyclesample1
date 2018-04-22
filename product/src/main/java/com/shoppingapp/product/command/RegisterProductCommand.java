package com.shoppingapp.product.command;

import com.shoppingapp.common.vo.ProductPricingCategory;
import com.shoppingapp.common.vo.QuantityUnit;
import com.shoppingapp.common.vo.SensitivityCharacteristic;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class RegisterProductCommand {

    @TargetAggregateIdentifier
    @NotNull
    private String productId;
    @NotNull
    private String productName;
    @NotNull
    private String categoryId;
    @NotNull
    private String subCategoryId;
    @NotNull
    private long quantity;
    @NotNull
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private ProductPricingCategory productPricingCategory;
    private double purchasePrice;
    private double MRP;
    private LocalDate creationDate;

    public RegisterProductCommand(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, ProductPricingCategory productPricingCategory, double purchasePrice, double MRP,LocalDate creationDate) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
        this.productPricingCategory = productPricingCategory;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.creationDate=creationDate;
    }

    public RegisterProductCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public long getQuantity() {
        return quantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    public List<String> getSubstitutes() {
        return substitutes;
    }

    public List<String> getComplements() {
        return complements;
    }


    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
