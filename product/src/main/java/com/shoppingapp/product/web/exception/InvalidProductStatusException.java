package com.shoppingapp.product.web.exception;

import com.shoppingapp.common.vo.ProductStatus;

public class InvalidProductStatusException extends Exception {

    private static final String message = "For product %s, while changing status to %s, " +
            "status %s not found in status list";

    public InvalidProductStatusException(String message) {
        super(message);
    }

    public static InvalidProductStatusException build(String productId,
                                                      ProductStatus actualStatus,
                                                      ProductStatus expectedStatus) {
        return new InvalidProductStatusException(String.format(message, productId, actualStatus, expectedStatus));
    }

}
