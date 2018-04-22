package com.shoppingapp.product.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.common.vo.ProductStatus;
import com.shoppingapp.product.query.repository.CategoryDetailsViewRepository;
import com.shoppingapp.product.query.repository.ProductActivationStatusViewRepository;
import com.shoppingapp.product.query.repository.ProductViewRepository;
import com.shoppingapp.product.query.repository.TaggedPriceVersionsViewRepository;
import com.shoppingapp.product.query.view.CategoryDetailsView;
import com.shoppingapp.product.query.view.ProductView;
import com.shoppingapp.product.query.view.TaggedPriceVersionsView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Component
public class ProductViewController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductViewController.class);
    private final ProductViewRepository productViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    private final CategoryDetailsViewRepository categoryDetailsViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;


    @Autowired
    public ProductViewController(ProductViewRepository productViewRepository,
                             TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository,
                             CategoryDetailsViewRepository categoryDetailsViewRepository,
                             ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productViewRepository = productViewRepository;
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
        this.categoryDetailsViewRepository=categoryDetailsViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }


    @RequestMapping(method = RequestMethod.GET, value = "id/{productId}")
    public ResponseEntity<ProductView> getProductById (@PathVariable String productId) {
        ProductView productView = productViewRepository.findOne(productId);
        return new ResponseEntity<ProductView>(productView, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "name/{productName}")
    public ResponseEntity<List<ProductView>> getProductByName (@PathVariable String productName) {
        List<ProductView> productView = productViewRepository.findByProductNameRegex(productName);
        return new ResponseEntity<List<ProductView>>(productView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/id/{categoryId}")
    public ResponseEntity<CategoryDetailsView> getProductCategoryByCategoryId (@PathVariable String categoryId) {
        CategoryDetailsView categoryDetailsView = categoryDetailsViewRepository.findOne(categoryId);
        return new ResponseEntity<CategoryDetailsView>(categoryDetailsView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/name/{categoryName}")
    public ResponseEntity<CategoryDetailsView> getProductCategoryByCategoryName (@PathVariable String categoryName) {
        List<CategoryDetailsView> categoryDetailsViews = categoryDetailsViewRepository.findByCategoryNameRegex(categoryName);
        //because category name should be unique the list is expected to return single element,if found record matching to the categoryName
        if(categoryDetailsViews!=null && !categoryDetailsViews.isEmpty()) {
            return new ResponseEntity<CategoryDetailsView>(categoryDetailsViews.get(0), HttpStatus.OK);
        }else{
            return new ResponseEntity<CategoryDetailsView>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/parent/id/{categoryId}")
    public ResponseEntity<List<CategoryDetailsView>> getAllProductCategoriesUnderAParentCategory (@PathVariable String categoryId) {
        List<CategoryDetailsView> categoryDetailsViews = categoryDetailsViewRepository.findByParentCategoryId(categoryId);
        return new ResponseEntity<List<CategoryDetailsView>>(categoryDetailsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public String getAllProducts () throws JsonProcessingException {
        final List<ProductView> productViews = new ArrayList<>();
        productViewRepository.findAll().forEach(productView -> productViews.add(productView));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productViews);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mrp/{productId}")
    public Double getProductMRP (@PathVariable String productId) {
        final List <ProductView> productViews = new ArrayList<>();
        List<TaggedPriceVersionsView> taggedPriceVersionsView =
                taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(productId);
        return taggedPriceVersionsView.get(0).getMRP();
        //return new ResponseEntity<List<ProductView>>(productViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/active/productsIds")
    public String getAllActiveProductIds () throws JsonProcessingException {
        final List <String> productIds = new ArrayList<>();
        productActivationStatusViewRepository.findAllByProductStatusesIn(ProductStatus.PRODUCT_ACTIVATED)
                .forEach(productActivationStatusView -> productIds.add(productActivationStatusView.getProductId()));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productIds);
    }

} 
