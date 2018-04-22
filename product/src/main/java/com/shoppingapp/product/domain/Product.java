package com.shoppingapp.product.domain;

import com.shoppingapp.common.vo.PriceTaggedWithProduct;
import com.shoppingapp.common.vo.ProductPricingCategory;
import com.shoppingapp.common.vo.QuantityUnit;
import com.shoppingapp.common.vo.SensitivityCharacteristic;
import com.shoppingapp.product.command.SetProductPricingConfigurationCommand;
import com.shoppingapp.product.event.ProductPricingConfigurationSetEvent;
import com.shoppingapp.product.event.ProductRegisteredEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@AggregateRoot
public class Product {
    @AggregateIdentifier
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long netQuantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    @AggregateMember
    private ProductConfiguration productConfiguration;
    private boolean isProductActivated = false;
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;

    public Product() {

    }

    public Product(String productId, String productName, String categoryId, String subCategoryId, long netQuantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, ProductPricingCategory productPricingCategory, double purchasePrice, double MRP, LocalDate creationDate) {
        DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
        final String taggedPriceVersionId = productId + "$" + creationDate.toString(format);
        apply(new ProductRegisteredEvent(productId, productName, categoryId, subCategoryId, netQuantity, quantityUnit, substitutes, complements, productPricingCategory, taggedPriceVersionId,purchasePrice, MRP,creationDate));
    }


    @EventSourcingHandler
    public void on(ProductRegisteredEvent event) {
        this.productId = event.getProductId();
        this.productName = event.getProductName();
        this.categoryId = event.getCategoryId();
        this.subCategoryId = event.getSubCategoryId();
        this.netQuantity = event.getQuantity();
        this.quantityUnit = event.getQuantityUnit();
        this.substitutes = event.getSubstitutes();
        this.complements = event.getComplements();
        PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(event.getTaggedPriceVersionId(), event.getPurchasePrice(), event.getMrp(), event.getRegistrationDate());
        taggedPriceVersions = new TreeSet<>();
        this.addNewTaggedPriceVersion(taggedPriceVersion);
    }

    @EventSourcingHandler
    public void on(ProductPricingConfigurationSetEvent event) {
            this.productConfiguration = new ProductConfiguration(event.getProductId(),event.getPricingOptions(), event.getPricingStrategyType(),event.getCostHeaderTypes(),event.getContingencyStockPercentage());
    }

    public String getProductId() {
        return this.productId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public List<String> getSubstitutes() {
        return this.substitutes;
    }

    public List<String> getComplements() {
        return this.complements;
    }

    public String getProductName() {
        return this.productName;
    }

    public long getNetQuantity() {
        return this.netQuantity;
    }

    public QuantityUnit getQuantityUnit() {
        return this.quantityUnit;
    }

    public boolean isProductActivated() {
        return isProductActivated;
    }

    public void addNewTaggedPriceVersion(PriceTaggedWithProduct newTaggedPrice) {
        this.taggedPriceVersions.add(newTaggedPrice);
    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion() {
        return taggedPriceVersions.first();
    }

    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    public void setProductPricingConfiguration(SetProductPricingConfigurationCommand command) {
        apply(new ProductPricingConfigurationSetEvent(command.getProductId(), command.getPricingOptions(), command.getPricingStrategyType(),command.getCostHeaderTypes(),command.getContingencyStockPercentage()));
    }


}
