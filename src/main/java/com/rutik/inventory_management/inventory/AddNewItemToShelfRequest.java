package com.rutik.inventory_management.inventory;

//request DTO

import com.rutik.inventory_management.item.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

//above java 17 we can use record for sending immutable data
public record AddNewItemToShelfRequest (
    @NotBlank(message = "name cannot be blank")
    String name,

    @NotNull(message = "category cannot be null")
    Category category,

    @NotNull(message = "price cannot be null")
    @PositiveOrZero(message = "price must be greater or equal to zero")
    BigDecimal price,

    @NotBlank
    String sku,

    @NotBlank(message = "shelf name cannot be blank")
    String shelfName,

    @NotNull(message = "quantity cannot be null")
    @PositiveOrZero(message = "quantity must be greater or equal to zero")
    int quantity
) {}
