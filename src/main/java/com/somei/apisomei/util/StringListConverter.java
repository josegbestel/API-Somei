package com.somei.apisomei.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Convert
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String SPLIT_CHAR = ";";


    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        if(stringList != null)
            return String.join(SPLIT_CHAR, stringList);
        else{
//            List<String> list = new ArrayList<>();
//            list.add("nula");
//            return String.join(SPLIT_CHAR, list);

            return null;
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if(string == null){
            return null;
        }else{
            return Arrays.asList(string.split(SPLIT_CHAR));
        }
    }
}
