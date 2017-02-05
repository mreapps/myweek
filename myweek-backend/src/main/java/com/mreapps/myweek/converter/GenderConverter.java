package com.mreapps.myweek.converter;

import com.mreapps.myweek.enums.Gender;
import com.mreapps.myweek.util.EnumWithIdConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(Gender gender)
    {
        return EnumWithIdConverter.getId(gender);
    }

    @Override
    public Gender convertToEntityAttribute(Integer id)
    {
        return EnumWithIdConverter.getValue(id, Gender.values());
    }
}
