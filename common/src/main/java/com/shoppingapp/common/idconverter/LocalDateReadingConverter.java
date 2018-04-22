package com.shoppingapp.common.idconverter;

import com.mongodb.BasicDBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class LocalDateReadingConverter implements Converter<BasicDBObject, LocalDate> {

    @Override
    public LocalDate convert(BasicDBObject o) {
        return new LocalDate(o.getDate("endDate"));
    }
}
