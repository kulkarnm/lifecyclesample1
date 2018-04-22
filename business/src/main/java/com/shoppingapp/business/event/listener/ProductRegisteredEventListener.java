package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.ProductRegisteredEvent;
import com.shoppingapp.business.query.repository.BusinessAccountViewRepository;
import com.shoppingapp.business.query.repository.ProductViewRepository;
import com.shoppingapp.business.query.view.ProductView;
import com.shoppingapp.common.vo.ProductStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;
    private final CommandGateway commandGateway;
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository,BusinessAccountViewRepository businessAccountViewRepository,CommandGateway commandGateway) {
        this.itemRepository = repository;
        this.businessAccountViewRepository=businessAccountViewRepository;
        this.commandGateway=commandGateway;
    }


    @EventHandler
    public void on(ProductRegisteredEvent event) {
        final ProductView productView = new ProductView(
                event.getProductId(),
                event.getProductName(),
                event.getCategoryId(),
                event.getSubCategoryId(),
                event.getPurchasePrice(),
                event.getMrp(),
                ProductStatus.PRODUCT_REGISTERED
        );
        itemRepository.save(productView);
    }
}
