package com.mreapps.myweek.enums;

/**
 *
 */
public enum Gender implements EnumWithId
{
    MALE(1, "male"),
    FEMALE(2, "female");

    private int id;
    private String resourceKey;

    Gender(int id, String resourceKey)
    {
        this.id = id;
        this.resourceKey = resourceKey;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @SuppressWarnings("unused")
    public String getResourceKey()
    {
        return resourceKey;
    }
}
