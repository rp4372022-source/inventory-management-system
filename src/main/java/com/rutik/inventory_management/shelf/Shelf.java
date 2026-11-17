package com.rutik.inventory_management.shelf;

import com.rutik.inventory_management.item.Item;

import java.util.List;

public class Shelf {
    private int shelfId;
    private String shelfName;
    private List<Item> shelfItems;
    private int maxCapacity;

    public Shelf(){}

    public Shelf(int shelfId, String shelfName, List<Item> shelfItems, int maxCapacity) {
        this.shelfId = shelfId;
        this.shelfName = shelfName;
        this.shelfItems = shelfItems;
        this.maxCapacity = maxCapacity;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public List<Item> getShelfItems() {
        return shelfItems;
    }

    public void setShelfItems(List<Item> shelfItems) {
        this.shelfItems = shelfItems;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
