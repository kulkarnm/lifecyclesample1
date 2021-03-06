package com.shoppingapp.product.query.view;

import com.shoppingapp.common.vo.ProductStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ProductActivationStatusView")
public class ProductActivationStatusView {

    @Id
    private String productId;
    private List<ProductStatus> productStatuses;

    public ProductActivationStatusView() {

    }

    public ProductActivationStatusView(String productId, List<ProductStatus> productStatuses) {
        this.productId = productId;
        if(productStatuses == null) {
            this.productStatuses = new ArrayList<>();
        } else {
            this.productStatuses = productStatuses;
        }
    }

    public String getProductId() {
        return productId;
    }

    public List<ProductStatus> getProductStatuses() {
        return productStatuses;
    }

    public void addProductStatus(ProductStatus productStatus) {
            productStatuses.add(productStatus);

    }

}
