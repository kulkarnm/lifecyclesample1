package com.shoppingapp.business.query.view;

import com.shoppingapp.common.vo.ProductStatus;
import com.shoppingapp.common.vo.SensitivityCharacteristic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "ProductView")
public class ProductView {
    @Id
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private double purchasePrice;
    private double MRP;
    private ProductStatus productStatus;

    public ProductView(String productId, String productName, String categoryId,String subCategoryId,double purchasePrice, double MRP, ProductStatus productStatus) {
        this.productId = productId;
        this.productName=productName;
        this.categoryId=categoryId;
        this.subCategoryId=subCategoryId;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.productStatus = productStatus;

    }


    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
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
}
