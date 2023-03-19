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
//import com.example.prm_app_shopping.api.ApiServi;
import com.example.prm_app_shopping.api.ProductApiService;
import com.example.prm_app_shopping.api.ProductApiService;
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
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ArrayList<Product> products;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initProducts();
        initCategories();
    }
    void initCategories(){
        categories = new ArrayList<>();
        categories.add(new Category("Các loại Tivi ngon ", "", "#18ab4e", "Some description", 1));
        categories.add(new Category("Các loại Máy lạnh ngon", "", "#fb0504", "Some description", 2));
//        categories.add(new Category("Các loại Máy giặt ngon", "", "#4186ff", "Some description", 1));
//        categories.add(new Category("Các loại Máy lọc nước ngon", "", "#BF360C", "Some description", 1));
        categories.add(new Category("Các loại Bếp ngon", "", "#ff870e", "Some description", 3));
        categories.add(new Category("Tất cả", "", "#ff6f52", "Some description", 4));


        categoryAdapter = new CategoryAdapter(this, categories);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    void initProducts(){
        products = new ArrayList<>();
        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 0);
        ProductApiService.productApiService.getProductsByCategoryId(category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
//                Log.d("tagggg", String.valueOf(productList.get(0)));
                products.addAll(productList);
                productAdapter = new ProductAdapter(CategoryActivity.this, products);

                GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this, 2);
                binding.categoriesList.setLayoutManager(layoutManager);
                binding.categoriesList.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

//        productAdapter = new ProductAdapter(this, products);
//
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        binding.productList.setLayoutManager(layoutManager);
//        binding.productList.setAdapter(productAdapter);

    }
}