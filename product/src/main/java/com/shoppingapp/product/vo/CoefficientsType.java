package com.shoppingapp.product.vo;

public enum CoefficientsType {
    DEMAND_FUNCTION_COEFFICIENT(0), COST_FUNCTION_COEFFICIENT(1);

    private int typeCode;
    CoefficientsType(int typeCode) {
        this.typeCode=typeCode;
    }
}
