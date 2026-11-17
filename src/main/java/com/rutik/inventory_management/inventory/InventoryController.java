package com.rutik.inventory_management.inventory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add")
    public void add(@RequestBody Inventory inventory){
        System.out.println(inventory);
        inventoryService.add(inventory);
    }

    @GetMapping("/get/{id}")
    public Inventory get(@PathVariable int id){
        return inventoryService.get(id);
    }

    @PutMapping("/set/{id}")
    public void set(@PathVariable int id, @RequestBody Inventory inventory){
        inventoryService.set(id, inventory);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        inventoryService.delete(id);
    }

    @GetMapping("/getall")
    public List<Inventory> getAll(){
        return inventoryService.getAll();
    }
}
