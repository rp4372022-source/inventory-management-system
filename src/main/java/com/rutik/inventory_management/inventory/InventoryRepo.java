package com.rutik.inventory_management.inventory;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryRepo {

    private final JdbcTemplate jdbcTemplate;

    InventoryRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Inventory inventory) {
        String sql = "INSERT INTO inventory(inventory_id, shelf_id, item_id, item_quantity) VALUES (?,?,?,?);";
        jdbcTemplate.update(sql, inventory.getInventoryId(), inventory.getShelfId(),inventory.getItemId(), inventory.getItemQuantity());
    }

    public Inventory get(int inventoryId) {
        String sql = "SELECT * FROM inventory WHERE inventory_id = ?;";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Inventory.class), inventoryId);
    }

    public List<Inventory> getAll() {
        String sql = "SELECT * FROM inventory;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inventory.class));
    }

    public void set(int inventoryId, Inventory inventory) {
        String sql = "UPDATE inventory SET shelf_id = ?, item_id = ?, item_quantity = ? WHERE inventory_id = ?;";
        jdbcTemplate.update(sql, inventory.getShelfId(), inventory.getItemId(), inventory.getItemQuantity(),inventoryId);
    }

    public void delete(int inventoryId) {
        String sql = "DELETE FROM inventory WHERE inventory_id = ?";
        jdbcTemplate.update(sql, inventoryId);
    }

    public int getInventoryId(int shelfId, int itemId){
        try{
            String sql = "SELECT inventory_id FROM inventory WHERE shelf_id = ?, item_id = ?;";
            return jdbcTemplate.queryForObject(sql,Integer.class,shelfId,itemId);
        } catch (Exception e){
            return -1;
        }

    }
}
