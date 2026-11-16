package com.rutik.inventory_management.shelf;

import com.rutik.inventory_management.item.Item;

import java.util.List;

public class Shelf {
    private int id;
    private String name;
    private List<Item> items;
    private int availableSlots;
    private int maxCapacity;

    public Shelf(){}

    public Shelf(int id, String name, int availableSlots, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.availableSlots = availableSlots;
        this.maxCapacity = maxCapacity;
    }

    public Shelf(int id, String name, List<Item> items, int availableSlots, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.availableSlots = availableSlots;
        this.maxCapacity = maxCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", availableSlots=" + availableSlots +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
