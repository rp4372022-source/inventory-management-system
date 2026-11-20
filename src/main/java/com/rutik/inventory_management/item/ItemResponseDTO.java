package com.rutik.inventory_management.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ItemResponseDTO (
    Integer id,
    String name,
    Category category,
    BigDecimal price,
    String sku
){ }
