package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.ProductView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductViewRepository extends CrudRepository<ProductView, String> {
    List<ProductView> findAll();
}
