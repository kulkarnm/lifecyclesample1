package com.shoppingapp.product.query.repository;

import com.shoppingapp.product.query.view.CategoryDetailsView;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDetailsViewRepository extends CrudRepository<CategoryDetailsView, String> {
    @Query("{ 'categoryName' : { '$regex' : ?0 , $options: 'i'}}")
    List<CategoryDetailsView> findByCategoryNameRegex(String categoryName);
    List<CategoryDetailsView> findByParentCategoryId(String parentCategoryId);

}
