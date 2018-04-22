package com.shoppingapp.product.query.repository;

import com.shoppingapp.product.query.view.ProductView;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductViewPagingRepository extends PagingAndSortingRepository<ProductView, String> {
}
