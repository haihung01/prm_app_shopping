package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CategoryAdapter;
import com.example.prm_app_shopping.adapters.ProductAdapter;
//import com.example.prm_app_shopping.api.ApiServi;
import com.example.prm_app_shopping.adapters.SearchAdapter;
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
    ImageView menu;
    AutoCompleteTextView atcProductSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initProducts();
        initCategories();
        initDrawerLayout();
        setProductSearchAdapter();
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
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(categoryAdapter);
    }

    private  void initDrawerLayout(){
        menu = (ImageView) findViewById(R.id.iconMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.getRoot().openDrawer(GravityCompat.START);

            }
        });
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
                binding.productList.setLayoutManager(layoutManager);
                binding.productList.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
    public void setProductSearchAdapter() {
        products = new ArrayList<>();
        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 0);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://64085ddf8ee73db92e3eafad.mockapi.io/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ProductApiService productApiService = retrofit.create(ProductApiService.class);
//
//        Call<List<Product>> call = productApiService.getProducts();
//
//        call.enqueue(new Callback<List<Product>>() {
        ProductApiService.productApiService.getProductsByCategoryId(category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();

                    //set adapter
                    atcProductSearch = findViewById(R.id.atc_product_search);
                    SearchAdapter productSearchAdapter = new SearchAdapter(CategoryActivity.this, R.layout.item_search, productList);
                    atcProductSearch.setAdapter(productSearchAdapter);

//                     Sau khi chọn item search sẽ chuyển sang fragment detail
                    atcProductSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            ProductAdapter ab = new ProductAdapter(MainActivity.this, );
                            Intent intent = new Intent(CategoryActivity.this, ProductDetailActivity.class);
                            intent.putExtra("name", productList.get(position).getName());
                            intent.putExtra("image", productList.get(position).getImage());
                            intent.putExtra("price", productList.get(position).getPrice());
                            intent.putExtra("status", productList.get(position).getStatus());
                            CategoryActivity.this.startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Failed to retrieve data from API", Toast.LENGTH_SHORT).show();
                Log.d("MYTAG", "onFailure: " + t.getMessage());
            }
        });

    }
}