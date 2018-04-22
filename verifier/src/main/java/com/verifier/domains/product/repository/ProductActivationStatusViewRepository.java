package com.verifier.domains.product.repository;

import com.shoppingapp.common.vo.ProductStatus;
import com.verifier.domains.product.view.ProductActivationStatusView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductActivationStatusViewRepository extends CrudRepository<ProductActivationStatusView, String>{
    ProductActivationStatusView findByProductId(String productId);
    List<ProductActivationStatusView> findAllByProductStatusesIn(ProductStatus productStatus);
}
