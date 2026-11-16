package com.rutik.inventory_management.shelf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelfService {
    @Autowired
    private ShelfRepo shelfRepo;

    public void add(Shelf shelf){
        shelfRepo.add(shelf);
    }

    public Shelf get(int id){
        return shelfRepo.get(id);
    }

    public void set(int id, Shelf shelf){
        shelfRepo.set(id, shelf);
    }

    public void delete(int id){
        shelfRepo.delete(id);
    }

    public List<Shelf> getAll(){
        return shelfRepo.getAll();
    }
}
