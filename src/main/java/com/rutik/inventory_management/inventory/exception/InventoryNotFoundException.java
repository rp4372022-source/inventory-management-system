package com.rutik.inventory_management.inventory.exception;

public class InventoryNotFoundException extends InventoryException {
    InventoryNotFoundException(String msg){
        super(msg);
    }
}
