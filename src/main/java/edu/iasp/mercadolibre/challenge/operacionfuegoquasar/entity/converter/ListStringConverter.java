package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Converter
public class ListStringConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return Objects.isNull(attribute) ? "" : String.join(SPLIT_CHAR,attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return Objects.isNull(dbData) ?
                Collections.emptyList() :
                Arrays.asList(dbData.split(SPLIT_CHAR));
    }
}
