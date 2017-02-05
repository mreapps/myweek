package com.mreapps.myweek.util;

import com.mreapps.myweek.enums.EnumWithId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 */
public class EnumWithIdConverterTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getId() throws Exception
    {
        int id = EnumWithIdConverter.getId(TestEnum.VALUE_1);
        assertEquals(id, 1);
    }

    @Test
    public void getIdNull() throws Exception
    {
        Integer id = EnumWithIdConverter.getId(null);
        //noinspection ConstantConditions
        assertNull(id);
    }

    @Test
    public void getValue() throws Exception
    {
        TestEnum value = EnumWithIdConverter.getValue(2, TestEnum.values());
        assertEquals(value, TestEnum.VALUE_2);
    }

    @Test
    public void getValueByUnsupportedId() throws Exception
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unsupported id for enum type TestEnum[]");
        EnumWithIdConverter.getValue(3, TestEnum.values());
    }

    private enum TestEnum implements EnumWithId
    {
        VALUE_1(1),
        VALUE_2(2);

        private int id;

        TestEnum(int id)
        {
            this.id = id;
        }

        @Override
        public int getId()
        {
            return id;
        }
    }

}
