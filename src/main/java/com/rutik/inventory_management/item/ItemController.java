package com.rutik.inventory_management.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public void addItem(@RequestBody ItemRequestDTO item){
        itemService.addItem(item);
    }

    @PutMapping("/update/{id}")
    public void updateItem(@PathVariable int id,@RequestBody ItemRequestDTO item){
        itemService.updateItemById(id, item);
    }

    @GetMapping("/get/{id}")
    public ItemResponseDTO getItem(@PathVariable Integer id){
        return itemService.getItemById(id);
    }
    @GetMapping("/getall")
    public List<ItemResponseDTO> getAll(){
        return itemService.getAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable int id){
        itemService.deleteItemById(id);
    }
}
