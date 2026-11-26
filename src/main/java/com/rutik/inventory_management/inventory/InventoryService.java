package com.rutik.inventory_management.inventory;

import com.rutik.inventory_management.exception.InventoryException;
import com.rutik.inventory_management.inventory.exception.InventoryNotFoundException;
import com.rutik.inventory_management.item.ItemRequestDTO;
import com.rutik.inventory_management.item.ItemService;
import com.rutik.inventory_management.shelf.ShelfService;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MaxValidatorForBigDecimal;
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

        //checking if adding new quantity exceeds quantity
        int shelfMaxQuantity = shelfService.getMaxCapacity(shelfId);
        int currentQuantity = inventoryRepo.getItemsQuantityCount(shelfId);
        int newQuantity = request.quantity();

        if(shelfMaxQuantity < currentQuantity + newQuantity)
            throw new InventoryException("Not enough space for new quantity");

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

        //adding new Quantity to old quantity
        int updatedQuantity = currentQuantity+newQuantity;

//        System.out.println(currentQuantity+" "+newQuantity+" "+shelfMaxQuantity+" "+currentQuantity);
        if(inventoryId == -1){
            inventoryRepo.add(new Inventory(
                    shelfId,
                    itemId,
                    updatedQuantity
            ));
        } else {
            inventoryRepo.set(inventoryId,new Inventory(
                    shelfId,
                    itemId,
                    updatedQuantity
            ));
        }
    }
    public Inventory get(int id){
        return inventoryRepo.get(id).orElseThrow(()->
                new InventoryNotFoundException("No inventory found with id : "+id)
        );
    }
    public void set(int id,Inventory inventory){
        boolean isUpdated = inventoryRepo.set(id, inventory);
        if(!isUpdated){
            throw new InventoryNotFoundException("No inventory found with id : "+id);
        }
    }
    public void delete(int id){
        inventoryRepo.delete(id);
    }
    public List<InventoryResponseDTO> getAll(){
        return inventoryRepo.getAll();
    }
}