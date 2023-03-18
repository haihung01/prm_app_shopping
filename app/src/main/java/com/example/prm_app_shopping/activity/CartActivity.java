package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CartAdapter;
import com.example.prm_app_shopping.databinding.ActivityCardBinding;
import com.example.prm_app_shopping.databinding.ActivityMainBinding;
import com.example.prm_app_shopping.model.Cart;
import com.example.prm_app_shopping.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnDataChangeListener{
    ListView listview;
    TextView total;
    Button button;
    ArrayList<Cart> carts;
    Cart cart;
    CartAdapter CartAdapter;
    ActivityCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

//       listview = (ListView) findViewById(R.id.cart_list);
//        total = (TextView) findViewById(R.id.total_price);
//        button = (Button) findViewById(R.id.checkout_button);
    }


    private void init() {
        carts =  new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("myCache", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("cartsList", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Cart>>() {}.getType();
        carts = gson.fromJson(json, type);

        CartAdapter = new CartAdapter(this,carts);
        CartAdapter.setOnDataChangeListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.cartList.setLayoutManager(layoutManager);
        binding.cartList.setAdapter(CartAdapter);
    }

    @Override
    public void onDataChanged(double data) {
        binding.totalPrice.setText("Thanh toan:"+data);
    }
}