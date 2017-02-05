package com.mreapps.myweek.util;

import com.mreapps.myweek.enums.EnumWithId;

/**
 *
 */
public class EnumWithIdConverter
{
    private EnumWithIdConverter()
    {
    }

    public static Integer getId(EnumWithId e)
    {
        return e == null ? null : e.getId();
    }

    public static <T extends EnumWithId> T getValue(Integer id, T[] values)
    {
        if (id == null)
        {
            return null;
        }

        for (T value : values)
        {
            if (value.getId() == id)
            {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported id for enum type " + values.getClass().getSimpleName());
    }

}
