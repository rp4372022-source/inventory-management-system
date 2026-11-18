package com.rutik.inventory_management.inventory;

//request DTO

import com.rutik.inventory_management.item.Category;

import java.math.BigDecimal;

//above java 17 we can use record for sending immutable data
public record AddNewItemToShelfRequest (
    String name,
    Category category,
    BigDecimal price,
    String sku,
    String shelfName,
    int quantity
) {}
