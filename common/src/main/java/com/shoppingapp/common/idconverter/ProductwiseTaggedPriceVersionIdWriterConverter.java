package com.shoppingapp.common.idconverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.shoppingapp.common.vo.ProductwiseTaggedPriceVersionId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ProductwiseTaggedPriceVersionIdWriterConverter implements Converter<ProductwiseTaggedPriceVersionId, DBObject> {
    public DBObject convert(ProductwiseTaggedPriceVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("taggedPriceVersionId", source.getTaggedPriceVersionId());
        return dbo;
    }

}
