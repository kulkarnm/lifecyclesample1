package com.verifier.domains.business.repository;

import com.shoppingapp.common.vo.ProductStatus;
import com.verifier.domains.business.view.ProductView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessProductViewRepository extends CrudRepository<ProductView, String> {
    ProductView findByProductId(String productId);

    List<ProductView> findByProductStatus(ProductStatus productStatus);
}
