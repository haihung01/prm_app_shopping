package com.example.prm_app_shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.activity.ProductDetailActivity;
import com.example.prm_app_shopping.databinding.CartBinding;
import com.example.prm_app_shopping.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<Cart> carts;

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        Glide.with(context)
                .load(cart.getProduct().getImage())
                .into(holder.binding.image);
        holder.binding.title.setText(cart.getProduct().getName());
        holder.binding.year.setText(cart.getProduct().getModel_year());
        holder.binding.price.setText((int) cart.getProduct().getPrice());
        holder.binding.quantity.setText((int) cart.getProduct().getDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("name", cart.getProduct().getName());
                intent.putExtra("image", cart.getProduct().getImage());
                intent.putExtra("price", cart.getProduct().getPrice());
                intent.putExtra("status", cart.getProduct().getStatus());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carts != null ? carts.size() : 0;
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
