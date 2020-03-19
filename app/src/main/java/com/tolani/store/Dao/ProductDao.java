package com.tolani.store.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tolani.store.Entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("DELETE FROM product_store")
    void deleteAllProducts();

    @Query("SELECT * FROM product_store ORDER BY uid")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product_store WHERE uid=:productId")
    LiveData<Product> getProduct(String productId);

}
