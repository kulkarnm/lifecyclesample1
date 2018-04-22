package com.shoppingapp.product.query.repository;

import com.shoppingapp.product.query.view.ProductView;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductViewRepository extends CrudRepository<ProductView, String> {
    @Query("{ 'productName' : { '$regex' : ?0 , $options: 'i'}}")
    List<ProductView> findByProductNameRegex(String productName);
}
