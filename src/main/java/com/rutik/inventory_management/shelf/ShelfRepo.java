package com.rutik.inventory_management.shelf;

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
        String sql = "UPDATE shelves SET shelf_name = ?, max_capacity =? WHERE id = ?;";
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
        String sql = "SELECT shelf_id FROM shelves WHERE shelf_name = ?;";
        try{
            return jdbcTemplate.queryForObject(sql, Integer.class, name);
        } catch (Exception e)
        {
            throw new RuntimeException("No shelf found with name"+name+" "+e);
        }

    }
}
