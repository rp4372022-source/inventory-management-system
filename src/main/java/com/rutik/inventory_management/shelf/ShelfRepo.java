package com.rutik.inventory_management.shelf;

import com.rutik.inventory_management.jdbc.Util;
import com.rutik.inventory_management.jdbc.TemplateConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShelfRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void add(Shelf shelf){
        String sql = "INSERT INTO shelves(shelf_name, available_slots, max_capacity) VALUES (?,?,?);";
        jdbcTemplate.update(sql, shelf.getName(), shelf.getAvailableSlots(), shelf.getMaxCapacity());
    }

    public Shelf get(int id){
        String sql = "SELECT * FROM shelves WHERE shelf_id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Shelf.class), id);
    }

    public void set(int id, Shelf shelf){
        String sql = "UPDATE shelves SET shelf_name = ?, available_slots = ?, max_capacity =? WHERE id = ?;";
        jdbcTemplate.update(sql, shelf.getName(), shelf.getAvailableSlots(), shelf.getMaxCapacity(), id);
    }

    public void delete(int id){
        String sql = "DELETE FROM shelves WHERE shelf_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Shelf> getAll(){
        String sql = "SELECT * FROM shelves;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelf.class));
    }
}
