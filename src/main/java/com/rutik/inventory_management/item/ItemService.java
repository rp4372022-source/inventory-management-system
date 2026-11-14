package com.rutik.inventory_management.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepo repo;

    public void addItem(Item item){
        if(item == null)
            throw new RuntimeException("Item cannot be empty");
        repo.addItem(item);
    }
    public void updateItemById(int id, Item item){
        if(id < 0)
            throw new RuntimeException("Id cannot be negative");
        if(item == null)
            throw new RuntimeException("Item cannot be empty");
        repo.updateItemById(id, item);
    }
    public Item getItemById(int id){
        if(id < 0)
            throw new RuntimeException("Id cannot be negative");
        return repo.getItemById(id);
    }
    public void deleteItemById(int id){
        if(id < 0)
            throw new RuntimeException("Id cannot be negative");
        repo.deleteItemById(id);
    }
    public List<Item> getAllItems(){
        return repo.getAllItems();
    }
}
