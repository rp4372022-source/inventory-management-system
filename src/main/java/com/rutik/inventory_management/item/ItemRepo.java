package com.rutik.inventory_management.item;

import org.springframework.stereotype.Repository;

import com.rutik.inventory_management.jdbc.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepo {
    //crud
    public void addItem(Item item){
        try (Connection connection = Util.getConnection()){
            String sql = "INSERT INTO items (item_name, item_category, item_price, sku) VALUES (?,?,?,?);";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,item.getName());
                ps.setString(2,item.getCategory().toString());
                ps.setBigDecimal(3,item.getPrice());
                ps.setString(4,item.getSku());
                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Item getItemById(int id){
        try (Connection connection = Util.getConnection()){
            String sql = "SELECT * FROM items WHERE item_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,id);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return new Item(
                            rs.getInt("item_id"),
                            rs.getString("item_name"),
                            Category.fromString(rs.getString("item_category")),
                            rs.getBigDecimal("item_price"),
                            rs.getString("sku")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void updateItemById(int id, Item item){
        try (Connection connection = Util.getConnection()){
            String sql = "UPDATE items set item_name = ?, item_category = ?, item_price = ?, sku = ? WHERE item_id = ?;";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,item.getName());
                ps.setString(2,item.getCategory().toString());
                ps.setBigDecimal(3,item.getPrice());
                ps.setString(4, item.getSku());
                ps.setInt(5,id);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void deleteItemById(int id){
        try (Connection connection = Util.getConnection()){
            String sql = "DELETE FROM items WHERE item_id = ?;";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Item> getAllItems(){
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = Util.getConnection()){
            String sql = "SELECT * FROM items;";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    itemList.add(new Item(
                            rs.getInt("item_id"),
                            rs.getString("item_name"),
                            Category.fromString(rs.getString("item_category")),
                            rs.getBigDecimal("item_price"),
                            rs.getString("sku")
                    ));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return itemList;
    }

    public int getItemIdBySku(String sku) {

        try (Connection connection = Util.getConnection()){
            String sql = "SELECT item_id FROM items WHERE sku = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, sku);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    return rs.getInt("item_id");
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return -1;
    }
}