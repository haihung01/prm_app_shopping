package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CategoryAdapter;
import com.example.prm_app_shopping.adapters.ProductAdapter;
import com.example.prm_app_shopping.api.CategoryApiService;
import com.example.prm_app_shopping.databinding.ActivityCategoryBinding;
import com.example.prm_app_shopping.databinding.ActivityMainBinding;
import com.example.prm_app_shopping.model.Category;
import com.example.prm_app_shopping.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;

    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initProducts();
    }

    void initProducts(){
        products = new ArrayList<>();
        Intent intent = getIntent();
        int category = intent.getIntExtra("category",0);
        CategoryApiService.CategoryApiService.getProductsByCategoryId(category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                products.addAll(productList);
                productAdapter = new ProductAdapter(CategoryActivity.this, products);

                GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this, 2);
                binding.categoriesProductList.setLayoutManager(layoutManager);
                binding.categoriesProductList.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
}