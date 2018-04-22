package com.shoppingapp.product.event.listener;

import com.shoppingapp.common.vo.ProductStatus;
import com.shoppingapp.common.vo.ProductwiseTaggedPriceVersionId;
import com.shoppingapp.product.event.ProductRegisteredEvent;
import com.shoppingapp.product.query.repository.ProductActivationStatusViewRepository;
import com.shoppingapp.product.query.repository.ProductViewRepository;
import com.shoppingapp.product.query.repository.TaggedPriceVersionsViewRepository;
import com.shoppingapp.product.query.view.ProductActivationStatusView;
import com.shoppingapp.product.query.view.ProductView;
import com.shoppingapp.product.query.view.TaggedPriceVersionsView;
import org.axonframework.eventhandling.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository,
                                          ProductActivationStatusViewRepository productActivationStatusViewRepository,TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository) {
        this.itemRepository = repository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.taggedPriceVersionsViewRepository=taggedPriceVersionsViewRepository;
    }


    @EventHandler
    public void on(ProductRegisteredEvent event){
            final ProductView productView = new ProductView(
                    event.getProductId(),
                    event.getProductName(),
                    event.getCategoryId(),
                    event.getSubCategoryId(),
                    event.getQuantity(),
                    event.getQuantityUnit(),
                    event.getSubstitutes(),
                    event.getComplements(),
                    event.getProductPricingCategory()
            );
            itemRepository.save(productView);
            ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId = new ProductwiseTaggedPriceVersionId(event.getProductId(),LocalDate.now().toString());
            TaggedPriceVersionsView taggedPriceVersionsView=new TaggedPriceVersionsView(productwiseTaggedPriceVersionId,event.getPurchasePrice(),event.getMrp(),event.getRegistrationDate(),new LocalDate(9999,12,31));
            taggedPriceVersionsViewRepository.save(taggedPriceVersionsView);

            final ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView(event.getProductId(), new ArrayList<>());
            productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
