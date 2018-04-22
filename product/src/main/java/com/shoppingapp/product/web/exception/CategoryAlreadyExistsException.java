package com.shoppingapp.product.web.exception;

public class CategoryAlreadyExistsException extends Exception  {
    private static final String message = "The category with Id %s, already exists ";

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public static CategoryAlreadyExistsException build(String categoryId ) {
        return new CategoryAlreadyExistsException(String.format(message, categoryId));
    }

}
