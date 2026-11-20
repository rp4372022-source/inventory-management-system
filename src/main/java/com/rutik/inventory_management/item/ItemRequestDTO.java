package com.rutik.inventory_management.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ItemRequestDTO( //request by user
    @NotBlank(message = "Item name cannot be null")
    String name,

    @NotNull(message = "category cannot be null")
    Category category,

    @NotNull(message = "price cannot be null")
    @PositiveOrZero(message = "price cannot be negative")
    BigDecimal price,

    @NotNull(message = "sku cannot be null")
    String sku
) { }
