package com.rutik.inventory_management.inventory;

public class Inventory {
    private int inventoryId;
    private int shelfId;
    private int itemId;
    private int itemQuantity;

    public Inventory(){}

    public Inventory(int inventoryId, int shelfId, int itemId, int quantity) {
        this.inventoryId = inventoryId;
        this.shelfId = shelfId;
        this.itemId = itemId;
        this.itemQuantity = quantity;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int quantity) {
        this.itemQuantity = quantity;
    }
}
