package com.rutik.inventory_management.shelf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    @Autowired
    ShelfService shelfService;

    @PostMapping("/add")
    public void add(@RequestBody Shelf shelf){
        System.out.println(shelf);
        shelfService.add(shelf);
    }

    @GetMapping("/get/{id}")
    public Shelf get(@PathVariable int id){
        return shelfService.get(id);
    }

    @PutMapping("/set/{id}")
    public void set(@PathVariable int id, @RequestBody Shelf shelf){
        shelfService.set(id, shelf);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        shelfService.delete(id);
    }

    @GetMapping("/getall")
    public List<Shelf> getAll(){
        return shelfService.getAll();
    }
}
