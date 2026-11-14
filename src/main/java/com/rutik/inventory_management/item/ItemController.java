package com.rutik.inventory_management.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("/add")
    public void addItem(@RequestBody Item item){
        itemService.addItem(item);
    }

    @PutMapping("/update/{id}")
    public void updateItem(@PathVariable int id,@RequestBody Item item){
        itemService.updateItemById(id, item);
    }

    @GetMapping("/get/{id}")
    public Item getItem(@PathVariable int id){
        return itemService.getItemById(id);
    }
    @GetMapping("/getall")
    public List<Item> getAll(){
        return itemService.getAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable int id){
        itemService.deleteItemById(id);
    }
}
