package com.shoppingapp.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalDate;

public class PriceTaggedWithProduct implements Comparable<PriceTaggedWithProduct> {
    private String taggedPriceVersionId;
    private double purchasePricePerUnit;
    private double breakEvenPrice;
    private double MRP;
    private LocalDate taggedStartDate;
    private LocalDate taggedEndDate;

    @JsonCreator
    public PriceTaggedWithProduct(@JsonProperty("taggedPriceVersionId")String taggedPriceVersionId, @JsonProperty("purchasePricePerUnit") double purchasePricePerUnit, @JsonProperty("MRP")double MRP, @JsonProperty("taggedStartDate")LocalDate taggedStartDate) {
        this.taggedPriceVersionId = taggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
        this.taggedEndDate= new LocalDate(9999,12,31);
    }

/*
    @JsonCreator
    public PriceTaggedWithProduct(@JsonProperty("taggedPriceVersionId")String taggedPriceVersionId, @JsonProperty("purchasePricePerUnit")double purchasePricePerUnit, @JsonProperty("MRP")double MRP, @JsonProperty("taggedStartDate")LocalDate taggedStartDate, @JsonProperty("taggedEndDate")LocalDate taggedEndDate) {
        this.taggedPriceVersionId = taggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
        this.taggedEndDate = taggedEndDate;
    }
*/

    public PriceTaggedWithProduct() {
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public LocalDate getTaggedStartDate() {
        return taggedStartDate;
    }

    public void setTaggedStartDate(LocalDate taggedStartDate) {
        this.taggedStartDate = taggedStartDate;
    }

    public LocalDate getTaggedEndDate() {
        return taggedEndDate;
    }

    public void setTaggedEndDate(LocalDate taggedEndDate) {
        this.taggedEndDate = taggedEndDate;
    }

    public String getTaggedPriceVersionId() {
        return taggedPriceVersionId;
    }

    public void setTaggedPriceVersionId(String taggedPriceVersionId) {
        this.taggedPriceVersionId = taggedPriceVersionId;
    }

    public double getBreakEvenPrice() {
        return breakEvenPrice;
    }

    public void setBreakEvenPrice(double breakEvenPrice) {
        this.breakEvenPrice = breakEvenPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceTaggedWithProduct that = (PriceTaggedWithProduct) o;

        if (Double.compare(that.purchasePricePerUnit, purchasePricePerUnit) != 0) return false;
        if (Double.compare(that.MRP, MRP) != 0) return false;
        if (!taggedStartDate.equals(that.taggedStartDate)) return false;
        return taggedEndDate.equals(that.taggedEndDate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(purchasePricePerUnit);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(MRP);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + taggedStartDate.hashCode();
        result = 31 * result + taggedEndDate.hashCode();
        return result;
    }

    @Override
    public int compareTo(PriceTaggedWithProduct price2){
        if(this.taggedStartDate.isBefore(price2.getTaggedStartDate())){
            return 1;
        }else{
            return -1;
        }
    }
}
