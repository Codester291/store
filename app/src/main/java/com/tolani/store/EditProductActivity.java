package com.tolani.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.tolani.store.Entity.Product;
import com.tolani.store.ViewModel.ProductViewModel;

public class EditProductActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.tolani.store.EXTRA_ID";
    public static final String EXTRA_NAME = "com.tolani.store.EXTRA_NAME";
    public static final String EXTRA_PRICE = "com.tolani.store.EXTRA_PRICE";

    public static final String PRODUCT_ID = "product_id";
    private   String UPDATED_NAME;
    private String UPDATED_PRICE;
    private EditText product_name, product_price;
    private Intent intent;
    private int productId;
    private String words;
    private String pri;
    private LiveData<Product> productLiveData;

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        setTitle("Edit Product");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        product_name = findViewById(R.id.edit_text_edit_product);
        product_price = findViewById(R.id.edit_text_edit_price);

        intent = getIntent();
        if(intent!= null) {
            UPDATED_NAME= intent.getStringExtra(EXTRA_NAME);
            UPDATED_PRICE = intent.getStringExtra(EXTRA_PRICE);

            product_name.setText(UPDATED_NAME);
            product_price.setText(UPDATED_PRICE);
        }

        productViewModel = new ProductViewModel(getApplication());

        findViewById(R.id.edit_button).setOnClickListener(v -> {
            if(intent.hasExtra(PRODUCT_ID)) {
                productId = intent.getIntExtra(PRODUCT_ID, 0);
                words = product_name.getText().toString();
                pri = product_price.getText().toString();

                Product product = new Product( words, pri);
                product.setUid(productId);
                productViewModel.update(product);
                finish();
            }
        });

//        productViewModel = new ViewModelProvider
//                .AndroidViewModelFactory(getApplication())
//                .create(EditProductViewModel.class);
//        productLiveData = productViewModel.getProduct(productId);
//        productLiveData.observe(this, product -> {
//            if(product==null){return;}
//            produt_name.setText(product.getName());
//            product_price.setText(product.getPrice());
//        });
    }

//    public void updateProduct(View view) {
//
//    }


//    private  class UpdateProduct implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//            name = findViewById(R.id.edit_text_edit_product);
//            price = findViewById(R.id.edit_text_edit_price);
//
//            Intent data = new Intent();
//            data.putExtra(EXTRA_NAME, name.getText().toString());
//            data.putExtra(EXTRA_PRICE, price.getText().toString());
//
//            int id = getIntent().getIntExtra(EXTRA_ID, -1);
//            if (id != -1) {
//                data.putExtra(EXTRA_ID, id);
//            }
//            finish();
//        }
//    }
}
;
