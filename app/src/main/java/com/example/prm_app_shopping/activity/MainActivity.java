package com.example.prm_app_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CategoryAdapter;
import com.example.prm_app_shopping.adapters.ProductAdapter;
import com.example.prm_app_shopping.api.ProductApiService;
import com.example.prm_app_shopping.api.ProductApiService;
import com.example.prm_app_shopping.databinding.ActivityMainBinding;
import com.example.prm_app_shopping.model.Category;
import com.example.prm_app_shopping.model.Product;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ImageView card, history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategories();
        initProducts();
        initSlider();

//
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        // Set a listener to handle the button click event
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Do something when the button is clicked
//                Toast.makeText(MainActivity.this, "Floating Action Button Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
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

    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://tinhte.vn/store/2017/01/3949514_CV.png", "Tu lanh Panasonic promax"));
        binding.carousel.addData(new CarouselItem("https://s3.cloud.cmctelecom.vn/tinhte1/2017/05/4036405_image001.jpg", "Tivi LG Vip promax"));
        binding.carousel.addData(new CarouselItem("https://t4.ftcdn.net/jpg/02/61/01/87/360_F_261018762_f15Hmze7A0oL58Uwe7SrDKNS4fZIjLiF.jpg", "Giam gia den 45%"));


    }

    void initCategories(){
        categories = new ArrayList<>();
        categories.add(new Category("Các loại Tivi ngon ", "", "#18ab4e", "Some description", 1));
        categories.add(new Category("Các loại Máy lạnh ngon", "", "#fb0504", "Some description", 1));
//        categories.add(new Category("Các loại Máy giặt ngon", "", "#4186ff", "Some description", 1));
//        categories.add(new Category("Các loại Máy lọc nước ngon", "", "#BF360C", "Some description", 1));
        categories.add(new Category("Các loại Bếp ngon", "", "#ff870e", "Some description", 1));
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
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Xử lý lỗi nếu có
            }
        });

//        products = new ArrayList<Product>();
//
//        products.add(new Product("Tủ lạnh Family Hub",
//                "https://cdn11.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture/Apro/Apro_product_29826/tu-lanh-lg-inve_main_845_450.png.webp", "", 12, 12, 1, 1));
//        products.add(new Product("Máy lạnh Panasonic  ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX-4D8wC_xmUkxs-5DnvAlOc227my9GWQ9FqrdVOuozn3NHzPwYmcD3gFPN9NTzjYZttU&usqp=CAU", "", 13, 12, 1, 1));
//        products.add(new Product("Máy giặt Panasonic Inverter ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_6JWyFMoNBjwlEFCukBfC3tW8PVd8bK18eClb7uebP_SbqdeXO-QO6AIq0vXhPrZIFmk&usqp=CAU", "", 14, 12, 1, 1));
//        products.add(new Product("Bếp gas hồng ngoại Taka TK-HG9 ", "https://kingshop.vn/data/products/500/bep-gas-hong-ngoai-taka-tk-hg9-2.jpg", "", 15, 12, 1, 1));
//        products.add(new Product("Tivi SamSung", "https://cdn.tgdd.vn/Files/2018/02/03/1064416/top-5-tivi-samsung-ban-chay-nhat-thang-1-20181-2.jpg", "", 16, 12, 1, 1));
//        products.add(new Product("Cây nước nóng lạnh Kangaroo KG39H", "https://bizweb.dktcdn.net/100/075/453/products/39h-4.jpg?v=1510199027280", "", 17, 12, 1, 1));
//
//
//        productAdapter = new ProductAdapter(this, products);
//
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        binding.productList.setLayoutManager(layoutManager);
//        binding.productList.setAdapter(productAdapter);

    }

}
