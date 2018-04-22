package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.ProductActivatedEvent;
import com.shoppingapp.business.query.repository.ProductViewRepository;
import com.shoppingapp.business.query.view.ProductView;
import com.shoppingapp.common.vo.ProductStatus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductActivatedEventListener {
    private ProductViewRepository productViewRepository;

    @Autowired
    public ProductActivatedEventListener(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }


    @EventHandler
    public void on(ProductActivatedEvent event) {
        final ProductView productView = productViewRepository.findByProductId(event.getProductId());
        productView.setProductStatus(ProductStatus.PRODUCT_ACTIVATED);
        productViewRepository.save(productView);
    }
}
