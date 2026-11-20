package com.rutik.inventory_management.item;

import java.math.BigDecimal;

class Item { // making class default cuz dto's can handle
    private Integer id; //changed from int to Integer so it can be null and can catch if not present
    private String name;
    private Category category;
    private BigDecimal price;
    private String sku;

    public Item() {} //required for spring/jackson

    public Item(String name, Category category, BigDecimal price, String sku) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.sku = sku;
    }

    public Item(int id, String name, Category category, BigDecimal price, String sku) {
        this(name,category,price,sku);
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
