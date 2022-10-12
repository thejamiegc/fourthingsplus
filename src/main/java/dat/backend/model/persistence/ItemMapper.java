package dat.backend.model.persistence;

import dat.backend.model.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ItemMapper {
    static List<Item> getAll(ConnectionPool connectionPool){
        List<Item> itemList = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "select * from item";
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    boolean done = rs.getBoolean("done");
                    Timestamp created = rs.getTimestamp("created");
                    String username = rs.getString("username");

                    Item item = new Item(id,name,done,created,username);
                    itemList.add(item);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemList;
    }
}
