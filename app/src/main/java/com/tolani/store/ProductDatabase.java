package com.tolani.store;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tolani.store.Dao.ProductDao;
import com.tolani.store.Entity.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;
    public abstract ProductDao productDao();
    public static final String DATABASE_NAME = "store_database";

    public static synchronized ProductDatabase getInstance(Context context) {
        if( instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProductDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        public PopulateDbAsyncTask(ProductDatabase pd) {
            productDao = pd.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insertProduct(new Product("Strawberry Froyo", "5.00"));
            productDao.insertProduct(new Product("Hannah Banana", "6.99"));
            productDao.insertProduct(new Product("Busta Lime", "2.78"));
            return null;
        }
    }
}
