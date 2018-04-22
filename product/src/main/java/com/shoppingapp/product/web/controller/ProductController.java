package com.shoppingapp.product.web.controller;

import com.shoppingapp.common.generator.IdGenerator;
import com.shoppingapp.common.vo.SensitivityCharacteristic;
import com.shoppingapp.product.command.RegisterProductCommand;
import com.shoppingapp.product.query.repository.CategoryDetailsViewRepository;
import com.shoppingapp.product.query.repository.ProductActivationStatusViewRepository;
import com.shoppingapp.product.query.repository.ProductViewRepository;
import com.shoppingapp.product.query.repository.TaggedPriceVersionsViewRepository;
import com.shoppingapp.product.query.view.CategoryDetailsView;
import com.shoppingapp.product.web.exception.CategoryAlreadyExistsException;
import com.shoppingapp.product.web.request.RegisterCategoryRequest;
import com.shoppingapp.product.web.request.RegisterProductRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
@Component
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final CommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    private final CategoryDetailsViewRepository categoryDetailsViewRepository;
    private final IdGenerator defaultIdGenerator;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ProductController(CommandGateway commandGateway, ProductViewRepository productViewRepository,
                             TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository,
                             CategoryDetailsViewRepository categoryDetailsViewRepository,
                             IdGenerator defaultIdGenerator, ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
        this.categoryDetailsViewRepository=categoryDetailsViewRepository;
        this.defaultIdGenerator = defaultIdGenerator;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }
    @RequestMapping(method = RequestMethod.POST, value="category")
    public ResponseEntity<Object> registerCategory(@RequestBody RegisterCategoryRequest request) throws Exception {
        String IDString = request.getCategoryName() +"$" + request.getParentCategoryId();
        final String categoryId = defaultIdGenerator.generator(IDString);
        CategoryDetailsView categoryDetailsView = categoryDetailsViewRepository.findOne(categoryId);
        if(null == categoryDetailsView) {
            categoryDetailsView = new CategoryDetailsView(categoryId, request.getCategoryName(), request.getDescription(), request.getParentCategoryId());
            categoryDetailsViewRepository.save(categoryDetailsView);
        }else{
            throw CategoryAlreadyExistsException.build(categoryId);
        }
        ProductController.LOGGER.info("Category is created: "  + categoryId + " on date: " + LocalDate.now());
        final Map <String, String> map = new HashMap<>(1);
        map.put("id", categoryId);
        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.POST, value="register")
    public ResponseEntity<Object> registerProduct(@RequestBody @Valid RegisterProductRequest request) {
        String IDString = request.getProductName() + "$" + request.getCategoryId() + "$" + request.getSubCategoryId() + "$" + request.getQuantity();
        final String productId = defaultIdGenerator.generator(IDString);
        RegisterProductCommand createCommand = new RegisterProductCommand(
                productId,
                request.getProductName(),
                request.getCategoryId(),
                request.getSubCategoryId(),
                request.getQuantity(),
                request.getQuantityUnit(),
                Arrays.asList(request.getSubstitutes()),
                Arrays.asList(request.getComplements()),
                request.getProductPricingCategory(),
                request.getPurchasePrice(),
                request.getMrp(),LocalDate.now()
        );
        try {
            this.commandGateway.send(createCommand);
        } catch (Exception e) {
            throw e;
        }
        ProductController.LOGGER.info("Create product command send to Command gateway with product name: " + createCommand.getProductName() + " category:" + request.getCategoryId() + " subcategory: " + request.getSubCategoryId() + " on date: " + LocalDate.now());
        final Map <String, String> map = new HashMap<>(1);
        map.put("productId", productId);
        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
    }
}
