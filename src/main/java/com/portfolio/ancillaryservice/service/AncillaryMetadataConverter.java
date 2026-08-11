package com.portfolio.ancillaryservice.service;

import com.portfolio.domain.AncillaryMetadata;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.ObjectMapper;

@Converter
public class AncillaryMetadataConverter implements AttributeConverter<AncillaryMetadata, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public String convertToDatabaseColumn(AncillaryMetadata attribute) {
        if (attribute == null){
            return null;
        }

        return objectMapper.writeValueAsString(attribute);

    }

    /**
     * Converts the data stored in the database column into the value
     * to be stored in the entity attribute.
     *
     * <p>Note that it is the responsibility of the converter writer
     * to specify the correct {@code dbData} type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers
     * are not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted value to be stored in the entity attribute
     */
    @Override
    public AncillaryMetadata convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()){
            return null;
        }
        try {
            return objectMapper.readValue(dbData, AncillaryMetadata.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting String to AncillaryMetadata", e);
        }
    }
}
