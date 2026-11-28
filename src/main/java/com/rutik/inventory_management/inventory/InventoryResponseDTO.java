package com.rutik.inventory_management.inventory;

public record InventoryResponseDTO(
        int inventoryId,
        int shelfId,
        String shelfName,
        int itemId,
        String sku,
        String itemName,
        int itemQuantity
) { }
