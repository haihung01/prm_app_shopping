package com.example.prm_app_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CategoryAdapter;
import com.example.prm_app_shopping.adapters.ProductAdapter;
import com.example.prm_app_shopping.adapters.SearchAdapter;
import com.example.prm_app_shopping.api.ProductApiService;
import com.example.prm_app_shopping.api.ProductApiService;
import com.example.prm_app_shopping.databinding.ActivityMainBinding;
import com.example.prm_app_shopping.model.Category;
import com.example.prm_app_shopping.model.Product;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;


    ImageView card, history, menu;
    androidx.recyclerview.widget.RecyclerView rcvProduct;

    MenuItem itemHome;
    AutoCompleteTextView atcProductSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rcvProduct = findViewById(R.id.productList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvProduct.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter(getListProduct());
//        rcvProduct.setAdapter(productAdapter);
//
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        rcvProduct.addItemDecoration(itemDecoration);

        navigationView = findViewById(R.id.navigationView);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuLogin: {
//                    drawerLayout.close();
                    startActivity(new Intent(MainActivity.this, Login.class));
                    return true;
                }
                case R.id.menuHome: {
//                    drawerLayout.close();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    return true;
                }
            }
            return true;
        });

        initDrawerLayout();
        initCategories();
        initProducts();
        initSlider();
        setProductSearchAdapter();
//        onOptionsItemSelected();
//        menuHome();

        card = (ImageView) findViewById(R.id.iconCard);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        history = (ImageView) findViewById(R.id.iconHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        });

    }

    private List<Product> getListProduct() {
        List<Product> list = new ArrayList<>();
//        list.add(new Product());

        return list;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId() ;
         if(id == R.id.menuLogin) {

             Intent intent = new Intent(MainActivity.this, Login.class);
             startActivity(intent);
             return true;
         }
         if(id == R.id.menuHome) {

             Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
             startActivity(intent2);
             return true;
         }
        return super.onOptionsItemSelected(item);
    }


    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://tinhte.vn/store/2017/01/3949514_CV.png", "Tu lanh Panasonic promax"));
        binding.carousel.addData(new CarouselItem("https://s3.cloud.cmctelecom.vn/tinhte1/2017/05/4036405_image001.jpg", "Tivi LG Vip promax"));
        binding.carousel.addData(new CarouselItem("https://t4.ftcdn.net/jpg/02/61/01/87/360_F_261018762_f15Hmze7A0oL58Uwe7SrDKNS4fZIjLiF.jpg", "Giam gia den 45%"));


    }

    void initCategories(){
        categories = new ArrayList<>();
        categories.add(new Category("Các loại Tivi ngon ", "", "#18ab4e", "Some description", 1));
        categories.add(new Category("Các loại Máy lạnh ngon", "", "#fb0504", "Some description", 2));
        categories.add(new Category("Các loại Bếp ngon", "", "#ff870e", "Some description", 3));
        categories.add(new Category("Khác", "", "#ff6f52", "Some description", 1));


        categoryAdapter = new CategoryAdapter(this, categories);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }



    void initProducts(){
        ArrayList<Product> productsList = new ArrayList<>();
        ProductApiService.productApiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();
                    productsList.addAll(productList);
                    // Tiếp tục xử lý danh sách sản phẩm tại đây

                    productAdapter = new ProductAdapter(MainActivity.this, productsList);

                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                    binding.productList.setLayoutManager(layoutManager);
                    binding.productList.setAdapter(productAdapter);
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    public void setProductSearchAdapter() {

        ProductApiService.productApiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();

                    //set adapter
                    atcProductSearch = findViewById(R.id.atc_product_search);
                    SearchAdapter productSearchAdapter = new SearchAdapter(MainActivity.this, R.layout.item_search, productList);
                    atcProductSearch.setAdapter(productSearchAdapter);

//                     Sau khi chọn item search sẽ chuyển sang fragment detail
                    atcProductSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            ProductAdapter ab = new ProductAdapter(MainActivity.this, );
                            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                            intent.putExtra("name", productList.get(position).getName());
                            intent.putExtra("image", productList.get(position).getImage());
                            intent.putExtra("price", productList.get(position).getPrice());
                            intent.putExtra("status", productList.get(position).getStatus());
                            MainActivity.this.startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to retrieve data from API", Toast.LENGTH_SHORT).show();
                Log.d("MYTAG", "onFailure: " + t.getMessage());
            }
        });

    }

}
