package com.rutik.inventory_management.item;

import com.rutik.inventory_management.exception.ItemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepo repo;

    public ItemService(ItemRepo itemRepo){
        repo = itemRepo;
    }

    public void addItem(ItemRequestDTO itemRequestDTO){
        repo.addItem(toItem(itemRequestDTO));
    }
    public void updateItemById(int id, ItemRequestDTO item){
        repo.updateItemById(id, toItem(item));
    }
    public ItemResponseDTO getItemById(int id){
        Item item = repo.getItemById(id);
        if(item == null){
            throw new ItemException("Item not found with id :"+id);
        }
        return toItemResponseDTO(item);
    }
    public void deleteItemById(int id){
        repo.deleteItemById(id);
    }
    public List<ItemResponseDTO> getAllItems(){
        return repo.getAllItems().stream()
                .map(item -> toItemResponseDTO(item))
                .toList();
    }

    public Integer getItemIdBySku(String sku){
        if(sku == null){
            throw new IllegalArgumentException("sku cannot be null");
        }
        if(sku.isEmpty()){
            throw new IllegalArgumentException("sku cannot be empty");
        }
        return repo.getItemIdBySku(sku);
    }

    private Item toItem(ItemRequestDTO itemRequestDTO){
        return new Item(
                itemRequestDTO.name(),
                itemRequestDTO.category(),
                itemRequestDTO.price(),
                itemRequestDTO.sku()
        );
    }

    private ItemResponseDTO toItemResponseDTO(Item item){
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getPrice(),
                item.getSku()
        );
    }
}
