package com.shoppingapp.product.web.controller;

import com.shoppingapp.product.command.SetProductPricingConfigurationCommand;
import com.shoppingapp.product.query.repository.ProductViewRepository;
import com.shoppingapp.product.query.view.ProductView;
import com.shoppingapp.product.vo.CostHeaderType;
import com.shoppingapp.product.web.exception.ProductNotFoundException;
import com.shoppingapp.product.web.request.ProductPricingConfigurationRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
@Component
public class ProductConfigurationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final CommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;

    @Autowired
    public ProductConfigurationController(CommandGateway commandGateway, ProductViewRepository productViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "config/{productId}")
    public ResponseEntity<Object> setProductPricingConfiguration(@PathVariable String productId, @RequestBody @Valid ProductPricingConfigurationRequest request) throws Exception {
        final ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        List<CostHeaderType> costHeaderTypes = Arrays.asList(request.getCostHeaderTypes());
        SetProductPricingConfigurationCommand command = new SetProductPricingConfigurationCommand(
                productId, request.getActualsAggregationPeriodForTargetForecast(), request.getTargetChangeThresholdForPriceChange(), request.isCrossPriceElasticityConsidered(), request.isAdvertisingExpensesConsidered(), request.getPricingOptions(), request.getPricingStrategyType(), request.getDemandCurvePeriod(),request.getTentativePercentageChangeInProductDemand(), costHeaderTypes,request.getContingencyStockPercentage());
        try {
            this.commandGateway.send(command);
        } catch (Exception e) {
            throw e;
        }
        final Map<String, String> map = new HashMap<>(1);
        map.put("productId", productId);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

}
