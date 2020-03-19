package com.tolani.store.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tolani.store.Dao.ProductDao;
import com.tolani.store.Entity.Product;
import com.tolani.store.ProductDatabase;

public class EditProductViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private ProductDatabase db;
    private ProductDao productDao;

    public EditProductViewModel(@NonNull Application application) {
        super(application);
        Log.e(TAG, "Edit View Model");
        db = ProductDatabase.getInstance(application);
        productDao = db.productDao();
    }

    public LiveData<Product> getProduct(String productId) {
        return productDao.getProduct(productId);
    }
}
