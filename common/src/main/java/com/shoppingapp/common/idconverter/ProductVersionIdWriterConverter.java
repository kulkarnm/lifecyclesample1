package com.shoppingapp.common.idconverter;

import com.mongodb.BasicDBObject;
import com.shoppingapp.common.vo.ProductVersionId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ProductVersionIdWriterConverter implements Converter<ProductVersionId, BasicDBObject> {

    public BasicDBObject convert(ProductVersionId source) {
        BasicDBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("startDate", source.getStartDate().toDate());
        return dbo;
    }
}
