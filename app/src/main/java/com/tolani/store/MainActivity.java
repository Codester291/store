package com.tolani.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tolani.store.Adapter.ProductAdapter;
import com.tolani.store.Entity.Product;
import com.tolani.store.ViewModel.ProductViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    private ProductViewModel productViewModel;
//    public static final int ADD_PRODUCT_REQUEST_CODE = 1;
    public static final int EDIT_PRODUCT_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////////////////////////////////////
        //              Routes into the Add Product Activity           //
        /////////////////////////////////////////////////////////////////
        FloatingActionButton fab = findViewById(R.id.fab_add_product);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ProductAdapter adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);

        productViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(ProductViewModel.class);
        productViewModel.getAllProduct().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProduct(products);
            }
        });

        //////////////////////////////////////////////////////////////////
        //              Handles swiping to delete a product            //
        /////////////////////////////////////////////////////////////////
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                productViewModel.delete(adapter.getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Product Deleted successfully", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);
//
//        adapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
//            @Override
//            public void onProductClick(Product product) {
//                Intent i = new Intent(MainActivity.this, EditProductActivity.class);
//
//                i.putExtra("product_id", product.getUid());
////                i.putExtra(EditProductActivity.EXTRA_NAME, product.getName());
////                i.putExtra(EditProductActivity.EXTRA_PRICE, product.getPrice());
//                startActivityForResult(i, EDIT_PRODUCT_REQUEST_CODE);
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == EDIT_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(EditProductActivity.EXTRA_ID, -1);
//
//            if(id == -1) {
//                Toast.makeText(MainActivity.this, "Can't be updated", Toast.LENGTH_LONG).show();
//                return;
//            }
//
////            String product_name = data.getStringExtra(EditProductActivity.UPDATED_NAME);
////            String product_price = data.getStringExtra(EditProductActivity.UPDATED_PRICE);
//
//       //     Product product = new Product(product_name, product_price);
//            productViewModel.update(product);
//            Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_LONG).show();
//        }
//    }

    //////////////////////////////////////////////////////////////////
    //              Inflates the toolbar menu                      //
    /////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.add_product_menu, menu);
        return true;
    }

    private void openSearch() {
        Intent i = new Intent(this, SearchProduct.class);
        startActivity(i);
    }


    ///////////////////////////////////////////////////////////////////////////////////
    //           Handles the events that occurs when menu item is clicked           //
    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_product:
                openSearch();
                return true;
            case R.id.delete_product:
                productViewModel.deleteAll();
                Toast.makeText(MainActivity.this, "All products are vaporized", Toast.LENGTH_LONG);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onProductClick(Product product) {
        Intent i = new Intent(MainActivity.this, EditProductActivity.class);
//
        i.putExtra("product_id", product.getUid());
        i.putExtra(EditProductActivity.EXTRA_NAME, product.getName());
        i.putExtra(EditProductActivity.EXTRA_PRICE, product.getPrice());
        startActivity(i);
    }
}