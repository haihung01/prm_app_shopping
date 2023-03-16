package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CartAdapter;
import com.example.prm_app_shopping.databinding.ActivityCardBinding;
import com.example.prm_app_shopping.databinding.ActivityMainBinding;
import com.example.prm_app_shopping.model.Cart;
import com.example.prm_app_shopping.model.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView listview;
    TextView total;
    Button button;
    ArrayList<Cart> carts;
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
        carts.add(new Cart(1,1, new Product("1","1","1",1,1,1,1,1)));

        CartAdapter = new CartAdapter(this,carts);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.cartList.setLayoutManager(layoutManager);
        binding.cartList.setAdapter(CartAdapter);

    }
}