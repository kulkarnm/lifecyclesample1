package com.shoppingapp.product.query.repository;

import com.shoppingapp.common.vo.ProductStatus;
import com.shoppingapp.product.query.view.ProductActivationStatusView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductActivationStatusViewPagingRepository extends PagingAndSortingRepository<ProductActivationStatusView, String> {
    Page<ProductActivationStatusView> findAllByProductStatusesIn(ProductStatus productStatus, Pageable pageable);
}
