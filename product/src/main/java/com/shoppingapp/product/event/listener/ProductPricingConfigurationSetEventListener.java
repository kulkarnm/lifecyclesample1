package com.shoppingapp.product.event.listener;

import com.shoppingapp.common.vo.ProductStatus;
import com.shoppingapp.product.event.ProductPricingConfigurationSetEvent;
import com.shoppingapp.product.query.repository.ProductActivationStatusViewRepository;
import com.shoppingapp.product.query.repository.ProductConfigurationViewRepository;
import com.shoppingapp.product.query.view.ProductActivationStatusView;
import com.shoppingapp.product.query.view.ProductConfigurationView;
import com.shoppingapp.product.web.exception.ProductNotFoundException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductPricingConfigurationSetEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final CommandGateway CommandGateway;

    @Autowired
    public ProductPricingConfigurationSetEventListener(ProductConfigurationViewRepository productConfigurationViewRepository,
                                                       ProductActivationStatusViewRepository productActivationStatusViewRepository,
                                                       CommandGateway CommandGateway) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.CommandGateway=CommandGateway;
    }

    @EventHandler
    public void on(ProductPricingConfigurationSetEvent event) throws Exception {
        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(event.getProductId()).getProductStatuses();
            ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
            if (null == productConfigurationView) {
                productConfigurationView = new ProductConfigurationView(event.getProductId(), event.getPricingStrategyType(), event.getPricingOptions(),event.getCostHeaderTypes(),event.getContingencyStockPercentage());
            } else {
                productConfigurationView.setProductId(event.getProductId());
                productConfigurationView.setPricingOptions(event.getPricingOptions());
                productConfigurationView.setPricingStrategyType(event.getPricingStrategyType());
                productConfigurationView.setCostHeaderTypes(event.getCostHeaderTypes());
            }
            productConfigurationViewRepository.save(productConfigurationView);
            final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
            if (productActivationStatusView == null) {
                throw ProductNotFoundException.build(event.getProductId());
            }
            productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
