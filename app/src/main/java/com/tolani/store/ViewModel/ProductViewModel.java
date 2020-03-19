package com.tolani.store.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tolani.store.Entity.Product;
import com.tolani.store.Repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private LiveData<List<Product>> allProduct;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        allProduct = productRepository.getAllProducts();
    }

    public void insert(Product product) {
        productRepository.insert(product);
    }

    public void update(Product product) {
        productRepository.update(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void deleteAll() {
        productRepository.deleteAllProducts();
    }

    public LiveData<List<Product>> getAllProduct() {
        return allProduct;
    }
}
