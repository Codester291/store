package com.tolani.store.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tolani.store.Entity.Product;
import com.tolani.store.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> productList = new ArrayList<>();
    private OnProductClickListener mListener;
    public ProductAdapter( OnProductClickListener mListener){
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_items, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Product currentProduct = productList.get(position);
        holder.nameTextView.setText(currentProduct.getName());
        holder.priceTextView.setText("$" + currentProduct.getPrice());
    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public void setProduct(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    public Product getProductAt(int position) {
        return productList.get(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView priceTextView;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.product_name);
            this.priceTextView = itemView.findViewById(R.id.product_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onProductClick(productList.get(position));
                    }
                }
            });
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.mListener = listener;
    }
}
