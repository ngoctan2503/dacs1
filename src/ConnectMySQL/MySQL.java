package ConnectMySQL;

import com.raven.model.ModelItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {
    private static Connection conn = null;
    public static MySQL s = null;
    public static MySQL getConnection() {
        
        if(s == null) {
            s = new MySQL();
        }
        
        
        String url = "jdbc:mysql://localhost:3306/shopping";
        String user = "root";
        String password = "";
        
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối MySQL thành công");
            System.out.println(conn.getCatalog());
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return s;
    }
    
    public void addItem( String itemName, String description, int qty, double price, String brandName, double total) {
        try {
            String query = "INSERT INTO modelitem(itemName, description, qty, price, brandName, total) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, itemName);
            statement.setString(2, description);
            statement.setInt(3, qty);
            statement.setDouble(4, price);
            statement.setString(5, brandName);
            statement.setDouble(6, total);
           
           

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new item was inserted successfully!");
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add an item to the database: " + e.getMessage());
        }
    }
    public void addItem1( String itemName, String description, int qty, double price, String brandName, double total) {
        try {
            String query = "INSERT INTO orderpaid(itemName, description, qty, price, brandName, total) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, itemName);
            statement.setString(2, description);
            statement.setInt(3, qty);
            statement.setDouble(4, price);
            statement.setString(5, brandName);
            statement.setDouble(6, total);
           
           

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new item was inserted successfully!");
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add an item to the database: " + e.getMessage());
        }
    }
    public void deleteItem(int itemID) {
    try {
        String query = "DELETE FROM modelitem WHERE itemID = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, itemID);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("The item was deleted successfully!");
        }

        statement.close();
    } catch (SQLException e) {
        System.out.println("An error occurred while trying to delete the item: " + e.getMessage());
    }
}
    public void deleteItem1(int itemID) {
    try {
        String query = "DELETE FROM orderpaid WHERE itemID = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, itemID);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("The item was deleted successfully!");
        }

        statement.close();
    } catch (SQLException e) {
        System.out.println("An error occurred while trying to delete the item: " + e.getMessage());
    }
}
    
    public void updata(int itemID, double total, int qty){
        try {
            String query = "update modelitem set total = ?, qty = ? WHERE itemID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, total);
            statement.setInt(2, qty);
            statement.setInt(3, itemID);
            
            int rowsDeleted = statement.executeUpdate();
            

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<ModelItem> getItems() throws SQLException{
        List<ModelItem> items = new ArrayList<>();
        
        String sql = "select itemID, itemName, description, qty, price, brandName, total from modelitem;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()) {
            items.add(
                    new ModelItem(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getString(6), 
                            rs.getDouble(7)
                    )
            );
        }
        
        
        return items;
    }
    public List<ModelItem> getItems1() throws SQLException{
        List<ModelItem> items = new ArrayList<>();
        
        String sql = "select itemID, itemName, description, qty, price, brandName, total from orderpaid;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()) {
            items.add(
                    new ModelItem(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getString(6), 
                            rs.getDouble(7)
                    )
            );
        }
        
        
        return items;
    }
}