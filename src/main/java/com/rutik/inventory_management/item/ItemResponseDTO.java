package com.rutik.inventory_management.item;

import java.math.BigDecimal;

public record ItemResponseDTO (
    Integer id,
    String name,
    Category category,
    BigDecimal price,
    String sku
){ }
