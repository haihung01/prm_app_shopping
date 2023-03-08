package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        init();

        listview = (ListView) findViewById(R.id.cart_list);
        total = (TextView) findViewById(R.id.total_price);
        button = (Button) findViewById(R.id.checkout_button);
    }

    
    private void init() {

    }
}