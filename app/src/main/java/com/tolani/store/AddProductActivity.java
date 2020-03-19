package com.tolani.store;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.model.Font;
import com.tolani.store.Entity.Product;
import com.tolani.store.ViewModel.ProductViewModel;

public class AddProductActivity extends AppCompatActivity {

    private EditText name;
    private EditText price;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        setTitle("Add Product");

        productViewModel = new ProductViewModel(getApplication());

        findViewById(R.id.add_button).setOnClickListener(new saveNote());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        name = findViewById(R.id.edit_text_add_product);
        price = findViewById(R.id.edit_text_add_price);
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("Product Name should be provided");
        }
        if (TextUtils.isEmpty(price.getText())) {
            price.setError("Product Name should be provided");
        }
    }

    private class saveNote implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            name = findViewById(R.id.edit_text_add_product);
            price = findViewById(R.id.edit_text_add_price);

            Product product = new Product(name.getText().toString(), price.getText().toString());
            productViewModel.insert(product);

            finish();
        }
    }


}
