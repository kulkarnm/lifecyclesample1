package com.shoppingapp.common.idconverter;

import com.mongodb.BasicDBObject;
import com.shoppingapp.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;


@ReadingConverter
public class ProductVersionIdReaderConverter implements Converter<BasicDBObject, ProductVersionId> {
    @Override
    public ProductVersionId convert(BasicDBObject o) {

        String productId = (String) o.get("productId");

        ProductVersionId id = new ProductVersionId(productId, new LocalDate(o.getDate("startDate")));

        return id;
    }
}
