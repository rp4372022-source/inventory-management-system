package com.rutik.inventory_management.inventory;

import com.rutik.inventory_management.exception.InventoryException;
import com.rutik.inventory_management.item.ItemRequestDTO;
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
    public void add(AddNewItemToShelfRequest request){

        //checking if shelf has space
        int shelfId = shelfService.getShelfIdByName(request.shelfName());

        int availableSpaceInShelf = shelfService.getMaxCapacity(shelfId)
                - inventoryRepo.getItemsQuantityCount(shelfId);
        if(availableSpaceInShelf == 0){
            throw new InventoryException("No space available in shelf");
        }

        int itemId = itemService.getItemIdBySku(request.sku());
        // if no item id found then add as new
        if(itemId == -1){
            itemService.addItem(
                    new ItemRequestDTO(
                            request.name(),
                            request.category(),
                            request.price(),
                            request.sku()
                    )
            );
            // requesting again after new item added
            itemId = itemService.getItemIdBySku(request.sku());
        }

        // find the inventory id and save it
        Integer inventoryId = inventoryRepo.getInventoryId(shelfId, itemId);

        if(inventoryId == -1){
            inventoryRepo.add(new Inventory(
                    shelfId,
                    itemId,
                    request.quantity()
            ));
        } else {
            inventoryRepo.set(inventoryId,new Inventory(
                    shelfId,
                    itemId,
                    request.quantity()
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
    public List<InventoryResponseDTO> getAll(){
        return inventoryRepo.getAll();
    }
}