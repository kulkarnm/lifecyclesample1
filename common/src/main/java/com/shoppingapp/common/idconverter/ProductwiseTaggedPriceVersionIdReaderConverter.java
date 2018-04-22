package com.shoppingapp.common.idconverter;

import com.mongodb.DBObject;
import com.shoppingapp.common.vo.ProductwiseTaggedPriceVersionId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;


@ReadingConverter
public class ProductwiseTaggedPriceVersionIdReaderConverter implements Converter<DBObject, ProductwiseTaggedPriceVersionId> {
    @Override
    public ProductwiseTaggedPriceVersionId convert(DBObject o) {
        String productId = (String) o.get("productId");
        String taggedPriceVersionId = (String) o.get("taggedPriceVersionId");
        ProductwiseTaggedPriceVersionId id = new ProductwiseTaggedPriceVersionId(productId, taggedPriceVersionId);

        return id;
    }

}
