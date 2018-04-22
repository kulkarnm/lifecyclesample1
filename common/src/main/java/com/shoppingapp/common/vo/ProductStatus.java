package com.shoppingapp.common.vo;


public enum ProductStatus {


    //after product registration
    PRODUCT_REGISTERED(1),

    //dependent upon registration
    PRODUCT_CONFIGURED(2),

    //dependent upon registration
    PRODUCT_FORECASTED(3),
    //manual pseudoActuals for next day are assigned
    PRODUCT_STEPFORECAST_CREATED(4),

    BUSINESS_PROVISIONED(5),

    //opening price has been assigned.
    PRODUCT_PRICE_ASSIGNED(6),

    SUBSCRIPTION_RULES_CONFIGURED(7),

    PRODUCT_ACTIVATED(8),

    //If product is deactivated due to any exception or through forceful deactivation( taking our product from shoppingapp business)
    PRODUCT_DEACTIVATED(9);

    private int statusCode;

    ProductStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
