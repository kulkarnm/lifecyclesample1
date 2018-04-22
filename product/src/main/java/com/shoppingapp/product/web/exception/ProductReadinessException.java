package com.shoppingapp.product.web.exception;

import com.shoppingapp.common.vo.ProductStatus;

public class ProductReadinessException extends Exception {

    private static final String message = "Product %s is not ready for %s";

    public ProductReadinessException(String message) {
        super(message);
    }

    public static ProductReadinessException build(String productId,
                                                  ProductStatus status) {
        return new ProductReadinessException(String.format(message, productId, status));
    }

}
