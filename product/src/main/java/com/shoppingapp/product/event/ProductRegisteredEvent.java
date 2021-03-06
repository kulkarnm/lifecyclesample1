package com.shoppingapp.product.event;

import com.shoppingapp.common.vo.ProductPricingCategory;
import com.shoppingapp.common.vo.QuantityUnit;
import com.shoppingapp.common.vo.SensitivityCharacteristic;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public class ProductRegisteredEvent {
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long quantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private ProductPricingCategory productPricingCategory;
    private String taggedPriceVersionId;
    private double purchasePrice;
    private double mrp;
    private LocalDate registrationDate;

    public ProductRegisteredEvent(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, ProductPricingCategory productPricingCategory, String taggedPriceVersionId, double purchasePrice, double mrp, LocalDate registrationDate) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
        this.productPricingCategory = productPricingCategory;
        this.taggedPriceVersionId=taggedPriceVersionId;
        this.purchasePrice = purchasePrice;
        this.mrp = mrp;
        this.registrationDate=registrationDate;
    }

    public ProductRegisteredEvent() {
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

    public String getTaggedPriceVersionId() {
        return taggedPriceVersionId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMrp() {
        return mrp;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
