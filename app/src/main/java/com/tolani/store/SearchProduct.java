package com.tolani.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.tolani.store.Adapter.ProductAdapter;
import com.tolani.store.Entity.Product;
import com.tolani.store.ViewModel.ProductViewModel;

import java.util.List;

public class SearchProduct extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private static final String TAG = "SearchProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        setTitle("Search Product");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

//        RecyclerView recyclerView = findViewById(R.id.recycler_search);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        final ProductAdapter adapter = new ProductAdapter();
//        recyclerView.setAdapter(adapter);
//
//        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
