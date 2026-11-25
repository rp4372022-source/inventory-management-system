package com.rutik.inventory_management.inventory;

import com.rutik.inventory_management.exception.InventoryException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

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

    public Optional<Inventory> get(int inventoryId) {
        String sql = "SELECT * FROM inventory WHERE inventory_id = ?;";

        List<Inventory> result = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Inventory.class), inventoryId);
        return Optional.ofNullable(DataAccessUtils.singleResult(result));
    }

    public List<InventoryResponseDTO> getAll() {
//        String sql = "SELECT * FROM inventory;";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inventory.class));

        String sql = "select s.shelf_name as shelf_name, i.item_name as item_name, e.item_quantity as item_quantity from inventory e inner join shelves s on e.shelf_id = s.shelf_id inner join items i on e.item_id= i.item_id;";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(InventoryResponseDTO.class));
    }

    public void set(int inventoryId, Inventory inventory) {
        String sql = "UPDATE inventory SET shelf_id = ?, item_id = ?, item_quantity = ? WHERE inventory_id = ?;";
        jdbcTemplate.update(sql, inventory.getShelfId(), inventory.getItemId(), inventory.getItemQuantity(),inventoryId);
    }

    public void delete(int inventoryId) {
        String sql = "DELETE FROM inventory WHERE inventory_id = ?";
        jdbcTemplate.update(sql, inventoryId);
    }

    public int getInventoryId(int shelfId, int itemId) throws InventoryException{
        String sql = "SELECT inventory_id FROM inventory WHERE shelf_id = ? AND item_id = ?;";
        try {
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class, shelfId, itemId);
            return id != null ? id : -1;
        } catch (Exception e){
//            throw new InventoryException("Inventory doesn't exist for shelfId: " + shelfId + " and itemId: " + itemId);
            return -1;
        }
    }

    public int getItemsQuantityCount(int shelfId){
        try{
            String sql = "SELECT sum(item_quantity) FROM inventory WHERE shelf_id = ?";
            //return jdbcTemplate.queryForObject(sql,Integer.class,shelfId,itemId);
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, shelfId);
            return (count == null) ? 0 : count;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
