package com.shoppingapp.business.query.repository;

import com.shoppingapp.business.query.view.ProductView;
import com.shoppingapp.common.vo.ProductStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductViewRepository extends CrudRepository<ProductView, String> {
    ProductView findByProductId(String productId);

    List<ProductView> findByProductStatus(ProductStatus productStatus);
}
