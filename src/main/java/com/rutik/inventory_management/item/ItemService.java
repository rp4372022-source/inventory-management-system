package com.rutik.inventory_management.item;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepo repo;

    public ItemService(ItemRepo itemRepo){
        repo = itemRepo;
    }

    public void addItem(ItemRequestDTO item){
        if(item == null){
            throw new IllegalArgumentException("Item cannot be empty");
        }
        repo.addItem(item);
    }
    public void updateItemById(int id, ItemRequestDTO item){
        if(id < 0)
            throw new RuntimeException("Id cannot be negative");
        if(item == null)
            throw new RuntimeException("Item cannot be empty");
        repo.updateItemById(id, item);
    }
    public ItemResponseDTO getItemById(int id){
        if(id < 0)
            throw new RuntimeException("Id cannot be negative");
        return repo.getItemById(id);
    }
    public void deleteItemById(int id){
        if(id < 0)
            throw new RuntimeException("Id cannot be negative");
        repo.deleteItemById(id);
    }
    public List<ItemResponseDTO> getAllItems(){
        return repo.getAllItems();
    }

    public int getItemIdBySku(String sku){
        return repo.getItemIdBySku(sku);
    }

}
