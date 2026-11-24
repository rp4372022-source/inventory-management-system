package com.rutik.inventory_management.shelf;

import com.rutik.inventory_management.exception.ShelfException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelfRepo {

    private final JdbcTemplate jdbcTemplate;
    ShelfRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Shelf shelf){
        String sql = "INSERT INTO shelves(shelf_name, max_capacity) VALUES (?,?);";
        jdbcTemplate.update(sql, shelf.getShelfName(), shelf.getMaxCapacity());
    }

    public Shelf get(int id){
        String sql = "SELECT * FROM shelves WHERE shelf_id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Shelf.class), id);
    }

    public void set(int id, Shelf shelf){
        String sql = "UPDATE shelves SET shelf_name = ?, max_capacity =? WHERE shelf_id = ?;";
        jdbcTemplate.update(sql, shelf.getShelfName(), shelf.getMaxCapacity(), id);
    }

    public void delete(int id){
        String sql = "DELETE FROM shelves WHERE shelf_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Shelf> getAll(){
        String sql = "SELECT * FROM shelves;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelf.class));
    }

    public int getShelfIdByName(String name) {
        try{
            String sql = "SELECT shelf_id FROM shelves WHERE shelf_name = ?;";
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class, name);
            if(id == null)
                throw new ShelfException("Id not found with shelf name :"+name);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getMaxCapacityOfShelf(int shelfId) {
        try{
            String sql = "SELECT max_capacity FROM shelves WHERE shelf_id = ?";
            Integer maxCapacity = jdbcTemplate.queryForObject(sql, Integer.class, shelfId);

            if(maxCapacity == null) throw new ShelfException("No shelf found with shelf id "+shelfId);
            return maxCapacity;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
