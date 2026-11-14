package com.rutik.inventory_management.item;

import java.util.Locale;

public enum Category {
    UNKNOWN,
    ELECTRONICS;

    public static Category fromString(String str){
        if(str == null || str.isBlank()){
            return UNKNOWN;
        }
        try{
            return  Category.valueOf(str.trim().toUpperCase());
        } catch (IllegalArgumentException e){
            return UNKNOWN;
        }
    }
}
