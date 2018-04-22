package com.shoppingapp.product.domain.exception;

import com.shoppingapp.common.vo.ProductStatus;

public class ProductDeactivatedException extends Exception {
    private static final String message = "For product %s,current status is %s, ";

    public ProductDeactivatedException(String message) {
        super(message);
    }

    public static ProductDeactivatedException build(String productId,
                                                    ProductStatus actualStatus) {
        return new ProductDeactivatedException(String.format(message, productId, actualStatus));
    }
}
