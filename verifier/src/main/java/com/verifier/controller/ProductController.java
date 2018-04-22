package com.verifier.controller;

import com.verifier.domains.product.repository.*;
import com.verifier.domains.product.view.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    private final ProductBusinessAccountViewRepository productBusinessAccountViewRepository;
    private final CategoryDetailsViewRepository categoryDetailsViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final ProductViewPagingRepository productViewPagingRepository;
    private final ProductViewRepository productViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;

    @Autowired
    public ProductController(ProductBusinessAccountViewRepository productBusinessAccountViewRepository, CategoryDetailsViewRepository categoryDetailsViewRepository, ProductActivationStatusViewRepository productActivationStatusViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository, ProductViewPagingRepository productViewPagingRepository, ProductViewRepository productViewRepository,TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository) {
        this.productBusinessAccountViewRepository = productBusinessAccountViewRepository;
        this.categoryDetailsViewRepository = categoryDetailsViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.productViewPagingRepository = productViewPagingRepository;
        this.productViewRepository = productViewRepository;
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount")
    public ResponseEntity<BusinessAccountView> getBusinessAccount() {
        List<BusinessAccountView> views = productBusinessAccountViewRepository.findByEndDateAfter(LocalDate.now());
        return new ResponseEntity<>(views.get(0), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "categories")
    public ResponseEntity<List<CategoryDetailsView>> getAllCategories() {
        List<CategoryDetailsView> views = categoryDetailsViewRepository.findAll();
        return new ResponseEntity<>(views, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "activationstatus/{productId}")
    public ResponseEntity<ProductActivationStatusView> getProductActivationStatus(@PathVariable String productId) {
        ProductActivationStatusView activation = productActivationStatusViewRepository.findOne(productId);
        return new ResponseEntity<>(activation, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "configuration/{productId}")
    public ResponseEntity<ProductConfigurationView> getProductConfiguration(@PathVariable String productId) {
        ProductConfigurationView configuration = productConfigurationViewRepository.findOne(productId);
        return new ResponseEntity<>(configuration, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public ResponseEntity<ProductView> getProduct(@PathVariable String productId){
        ProductView productView=productViewRepository.findOne(productId);
        return new ResponseEntity<>(productView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<ProductView>> getProducts(){
        List<ProductView> productViews=productViewRepository.findAll();
        return new ResponseEntity<List<ProductView>>(productViews,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taggedprice/{productId}")
    public ResponseEntity<List<TaggedPriceVersionsView>> getTaggedPriceVersion(@PathVariable String productId){
        List<TaggedPriceVersionsView> taggedPrices = taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(productId);
        return new ResponseEntity<>(taggedPrices,HttpStatus.OK);
    }
}
