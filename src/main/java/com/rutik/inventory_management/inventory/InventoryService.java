package com.rutik.inventory_management.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepo inventoryRepo;

    InventoryService(InventoryRepo inventoryRepo){
        this.inventoryRepo = inventoryRepo;
    }
    public void add(Inventory inventory){
        inventoryRepo.add(inventory);
    }
    public Inventory get(int id){
        return inventoryRepo.get(id);
    }
    public void set(int id,Inventory inventory){
        inventoryRepo.set(id, inventory);
    }
    public void delete(int id){
        inventoryRepo.delete(id);
    }
    public List<Inventory> getAll(){
        return inventoryRepo.getAll();
    }
}
