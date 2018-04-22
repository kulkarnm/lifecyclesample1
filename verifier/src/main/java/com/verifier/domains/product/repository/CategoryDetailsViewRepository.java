package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.CategoryDetailsView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDetailsViewRepository extends CrudRepository<CategoryDetailsView, String> {
    List<CategoryDetailsView> findAll();
}
