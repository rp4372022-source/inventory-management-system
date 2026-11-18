package com.rutik.inventory_management.inventory;

import com.rutik.inventory_management.item.Item;
import com.rutik.inventory_management.item.ItemService;
import com.rutik.inventory_management.shelf.ShelfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    private final ItemService itemService;
    private final ShelfService shelfService;

    InventoryService(InventoryRepo inventoryRepo, ItemService itemService, ShelfService shelfService){
        this.inventoryRepo = inventoryRepo;
        this.itemService= itemService;
        this.shelfService = shelfService;
    }

    @Transactional //ensures all or nothing, something goes wrong entire transaction get roll back
    public void add(AddNewItemToShelfRequest addNewItemToShelfRequest){
        Item item = new Item(
                addNewItemToShelfRequest.name(),
                addNewItemToShelfRequest.category(),
                addNewItemToShelfRequest.price(),
                addNewItemToShelfRequest.sku()
        );

        //finding and updating item in items table
        int itemId = itemService.getItemIdBySku(addNewItemToShelfRequest.sku());
        if(itemId == -1){
            itemService.addItem(item);
            itemId = itemService.getItemIdBySku(addNewItemToShelfRequest.sku());
        } else {
            itemService.updateItemById(itemId, item);
        }
        // finding shelf id and updating inventory
        System.out.println(addNewItemToShelfRequest.shelfName());
        int shelfId = shelfService.getShelfIdByName(addNewItemToShelfRequest.shelfName());

        //find shelf id and item id in inventory
        int inventoryId = inventoryRepo.getInventoryId(shelfId, itemId);

        if(inventoryId == -1){
            inventoryRepo.add(new Inventory(
                    shelfId,
                    itemId,
                    addNewItemToShelfRequest.quantity()
            ));
        } else {
            inventoryRepo.set(inventoryId,new Inventory(
                    shelfId,
                    itemId,
                    addNewItemToShelfRequest.quantity()
            ));
        }
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
