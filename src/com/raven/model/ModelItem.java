package com.raven.model;

import java.text.DecimalFormat;
import javax.swing.Icon;

public class ModelItem {
     private int itemID;
    private String itemName;
    private String description;
    private int qty;
    private double price;
    private String brandName;
    private double total;
    private Icon image;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public ModelItem(int itemID, String itemName, String description, int qty, double price, String brandName, double total, Icon image) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.brandName = brandName;
        this.total = total;
        this.image = image; 
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    public ModelItem(int itemID, String itemName, String description, int qty, double price, String brandName, double total) {
    this.itemID = itemID;
    this.itemName = itemName;
    this.description = description;
    this.qty = qty;
    this.price = price;
    this.brandName = brandName;
    this.total = total;
}
     public ModelItem() {
    }

   
    
   public Object[] toTableRow(int rowNum) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        return new Object[]{this, df.format(rowNum), itemName, df.format(qty), "$ " + df.format(price), "$ " + df.format(total)};
    }
}
