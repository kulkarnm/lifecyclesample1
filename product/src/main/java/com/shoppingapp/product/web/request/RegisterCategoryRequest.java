package com.shoppingapp.product.web.request;

import javax.validation.constraints.NotNull;

public class RegisterCategoryRequest {
    @NotNull
    private String categoryName;
    @NotNull
    private String description;

    private String parentCategoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
