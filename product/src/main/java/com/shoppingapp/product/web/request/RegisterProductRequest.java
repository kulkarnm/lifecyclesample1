package com.shoppingapp.product.web.request;


import com.shoppingapp.common.vo.ProductPricingCategory;
import com.shoppingapp.common.vo.QuantityUnit;
import com.shoppingapp.common.vo.SensitivityCharacteristic;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class RegisterProductRequest {
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
    private String [] substitutes;
    private String [] complements;
    @NotNull
    private ProductPricingCategory productPricingCategory;
    @NotNull
    private double purchasePrice;
    @NotNull
    private double mrp;



    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public QuantityUnit getQuantityUnit() {
        return this.quantityUnit;
    }

    public void setQuantityUnit(QuantityUnit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String[] getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(String[] substitutes) {
        this.substitutes = substitutes;
    }

    public String[] getComplements() {
        return complements;
    }

    public void setComplements(String[] complements) {
        this.complements = complements;
    }


    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }
}
