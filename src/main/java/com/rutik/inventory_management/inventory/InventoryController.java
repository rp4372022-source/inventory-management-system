package com.rutik.inventory_management.inventory;

import jakarta.validation.Valid;
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
    public void add(@Valid @RequestBody AddNewItemToShelfRequest request){
        inventoryService.add(request);
    }

    @GetMapping("/get/{id}")
    public Inventory get(@PathVariable int id){
        return inventoryService.get(id);
    }

    @PutMapping("/set/{id}")
    public void set(@Valid @PathVariable int id, @Valid @RequestBody Inventory inventory){
        inventoryService.set(id, inventory);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@Valid @PathVariable int id){
        inventoryService.delete(id);
    }

    @GetMapping("/getall")
    public List<InventoryResponseDTO> getAll(){
        return inventoryService.getAll();
    }
}
