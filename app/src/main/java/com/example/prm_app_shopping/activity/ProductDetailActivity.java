package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.ProductAdapter;
import com.example.prm_app_shopping.databinding.ActivityProductDetailBinding;
import com.example.prm_app_shopping.model.Cart;
import com.example.prm_app_shopping.model.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;
    Product product;
    Cart cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String json = getIntent().getStringExtra("product");
        Gson gson = new Gson();
        product = gson.fromJson(json, Product.class);
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String status = getIntent().getStringExtra("status");

        double price = getIntent().getDoubleExtra("price", 0);

        Glide.with(this)
                .load(image)
                .into(binding.productImage);


        getSupportActionBar().setTitle(name);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // display the product details
        TextView productName = findViewById(R.id.textViewProductDetail);
        TextView productPrice = findViewById(R.id.textViewProductDetail2);
        TextView productNote = findViewById(R.id.textViewProductDetail3);

        productName.setText(name);
        productPrice.setText(String.valueOf(price)) ;
        productNote.setText(status);
    }


    public void clickAdd(View view) {
        cart= new Cart(product.getId(), product.getDiscount()* product.getPrice(), product);
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("cart", json);
        startActivity(intent);
        Toast.makeText(this, "Order has been placed. ", Toast.LENGTH_SHORT).show();
        startActivity(new Intent( ProductDetailActivity.this, MainActivity.class ));
        finish();
    }



// goi ten san pham tren header
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}