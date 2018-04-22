package com.shoppingapp.common.idconverter;

import com.mongodb.BasicDBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

public class LocalDateWritingConverter implements Converter<LocalDate, BasicDBObject> {
    public BasicDBObject convert(LocalDate date){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put("endDate", date.toDate());
        return dbo;
    }
}
