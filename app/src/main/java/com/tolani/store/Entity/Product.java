package com.tolani.store.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_store")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "price")
    private String price;

    @Ignore
    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Product(int uid, String name, String price) {
        this.uid = uid;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
