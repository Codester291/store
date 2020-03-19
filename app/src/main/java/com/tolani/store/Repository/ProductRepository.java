package com.tolani.store.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.tolani.store.Dao.ProductDao;
import com.tolani.store.Entity.Product;
import com.tolani.store.ProductDatabase;

import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application) {
        ProductDatabase pd = ProductDatabase.getInstance(application);
        productDao = pd.productDao();
        allProducts = productDao.getAllProducts();
    }


    public void insert(Product product) {
        new InsertAsyncTask(productDao).execute(product);
    }

    public void  update(Product product) {
        new UpdateAsyncTask(productDao).execute(product);
    }

    public void delete(Product product) {
        new DeleteAsyncTask(productDao).execute(product);
    }


    public void deleteAllProducts() {
        new DeleteAllAsyncTask(productDao).execute();
    }


    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    private static class InsertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public InsertAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insertProduct(products[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public UpdateAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.updateProduct(products[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public DeleteAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.deleteProduct(products[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProductDao productDao;

        public DeleteAllAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllProducts();
            return null;
        }
    }

}
