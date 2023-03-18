package com.example.prm_app_shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.activity.CartActivity;
import com.example.prm_app_shopping.activity.ProductDetailActivity;
import com.example.prm_app_shopping.databinding.CartBinding;
import com.example.prm_app_shopping.model.Cart;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<Cart> carts;
    CartActivity cartActivity = new CartActivity();


    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        updateData(cart.getTotal());
        Glide.with(context)
                .load(cart.getProduct().getImage())
                .into(holder.binding.image);
        holder.binding.title.setText(cart.getProduct().getName());
        holder.binding.year.setText("Year model:" + cart.getProduct().getModel_year());
        holder.binding.price.setText("$" + cart.getProduct().getPrice());
        holder.binding.quantity.setText(String.valueOf((int) cart.getProduct().getDiscount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("name", cart.getProduct().getName());
                intent.putExtra("image", cart.getProduct().getImage());
                intent.putExtra("price", cart.getProduct().getPrice());
                intent.putExtra("status", cart.getProduct().getStatus());
                Gson gson = new Gson();
                String json = gson.toJson(cart.getProduct());
                intent.putExtra("product", json);
                context.startActivity(intent);
            }
        });
        holder.binding.ButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = (int) cart.getProduct().getDiscount();
                if (quantity >= 1) {
                    holder.binding.quantity.setText(String.valueOf(quantity - 1));
                    cart.getProduct().setDiscount(quantity - 1);
                    double total=cart.getProduct().getPrice() * (quantity-1);
                    cart.setTotal(total);
                    updateData(total);
                }
            }
        });
        holder.binding.ButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = (int) cart.getProduct().getDiscount();
                holder.binding.quantity.setText(String.valueOf(quantity + 1));
                cart.getProduct().setDiscount(quantity + 1);
                double total=cart.getProduct().getPrice() * (quantity+1);
                cart.setTotal(total);
                updateData(total);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carts != null ? carts.size() : 0;
    }

    public interface OnDataChangeListener {
        void onDataChanged(double data);
    }
    private OnDataChangeListener mListener;
    public void setOnDataChangeListener(OnDataChangeListener listener) {
        mListener = listener;
    }
    public void updateData(double newData) {
        // cập nhật số liệu biến
        double mData = newData;

        // gọi phương thức callback
        if (mListener != null) {
            mListener.onDataChanged(mData);
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title, year, quantity, price;

        CartBinding binding;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartBinding.bind(itemView);
            img = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
        }
    }
}
