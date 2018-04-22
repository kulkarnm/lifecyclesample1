package com.shoppingapp.common.vo;

public enum QuantityUnit {
    GM("gram"), KG("kilogram"), LT("Litre"), ml("millilitre");

    private String unitName;

    QuantityUnit(String name) {
        this.unitName = name;
    }

    public String getUnitName() {
        return unitName;
    }

}
