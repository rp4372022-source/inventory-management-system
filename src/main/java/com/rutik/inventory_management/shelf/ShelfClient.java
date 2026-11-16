package com.rutik.inventory_management.shelf;

public class ShelfClient {
    private String name;
    private int available_slots;
    private int max_capacity;

    public ShelfClient(){}

    public ShelfClient(String name, int availableSlots, int maxCapacity) {
        this.name = name;
        this.available_slots = availableSlots;
        this.max_capacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableSlots() {
        return available_slots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.available_slots = availableSlots;
    }

    public int getMaxCapacity() {
        return max_capacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.max_capacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                ", name='" + name + '\'' +
                ", availableSlots=" + available_slots +
                ", maxCapacity=" + max_capacity +
                '}';
    }
}
