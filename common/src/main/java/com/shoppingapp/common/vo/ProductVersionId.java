package com.shoppingapp.common.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class ProductVersionId implements Serializable, Comparable<ProductVersionId> {
    private String productId;
    private LocalDate startDate;
    public ProductVersionId(String productId, LocalDate startDate) {
        this.productId = productId;
        this.startDate = startDate;
    }

    public ProductVersionId() {
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "ProductVersionId{" +
                "productId='" + productId + '\'' +
                ", startDate=" + startDate +
                '}';
    }

    @Override
    public int compareTo(ProductVersionId o) {
        return this.getStartDate().compareTo(o.getStartDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductVersionId that = (ProductVersionId) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        return startDate != null ? startDate.equals(that.startDate) : that.startDate == null;

    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }
}
