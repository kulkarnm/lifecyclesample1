package com.shoppingapp.product.web.exception;

public class ProductNotFoundException extends Exception {

    private String message;

    private ProductNotFoundException(String message) {
        this.message = message;
    }

    public static ProductNotFoundException build(String shoppingappableItemId) {
        return new ProductNotFoundException(String.format("Subscriptionable Item is not found with id: %s", shoppingappableItemId));
    }

    public static ProductNotFoundException build() {
        return new ProductNotFoundException(String.format("Product list exhausted"));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
