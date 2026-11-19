package com.rutik.inventory_management.inventory;

public record InventoryResponseDTO(
    String shelfName,
    String itemName,
    int itemQuantity
) { }
