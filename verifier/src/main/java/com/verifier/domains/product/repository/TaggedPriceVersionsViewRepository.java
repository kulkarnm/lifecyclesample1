package com.verifier.domains.product.repository;

import com.shoppingapp.common.vo.ProductwiseTaggedPriceVersionId;
import com.verifier.domains.product.view.TaggedPriceVersionsView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaggedPriceVersionsViewRepository extends CrudRepository<TaggedPriceVersionsView, ProductwiseTaggedPriceVersionId> {
    TaggedPriceVersionsView findOne(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId);
    List<TaggedPriceVersionsView> findByProductwiseTaggedPriceVersionId_ProductId(String productId);
}
