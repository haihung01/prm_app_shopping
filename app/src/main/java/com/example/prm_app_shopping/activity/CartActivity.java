package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prm_app_shopping.R;

public class CartActivity extends AppCompatActivity {
    ListView listview;
    TextView total;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

//        init();

        listview = (ListView) findViewById(R.id.cart_list);
        total = (TextView) findViewById(R.id.total_price);
        button = (Button) findViewById(R.id.checkout_button);
    }

//
//    public void clickAdd(View view) {
//        // Get the product information
//        String productName = getIntent().getStringExtra("name");
//        double productPrice = getIntent().getDoubleExtra("price", 0);
//        int productQuantity = Integer.parseInt(qtyValue.getText().toString());
//
//        // Create an Intent to start the CartActivity
//        Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
//
//        // Add the product information to the Intent
//        intent.putExtra("name", productName);
//        intent.putExtra("price", productPrice);
//        intent.putExtra("quantity", productQuantity);
//
//        // Start the CartActivity
//        startActivity(intent);
//    }
}