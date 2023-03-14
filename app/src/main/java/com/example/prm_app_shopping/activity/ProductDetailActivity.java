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
import com.example.prm_app_shopping.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;
//    ImageButton addQty, minusQty;
//    TextView qtyValue, totalTxt, price;
//    int q;
//    int p;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        price = findViewById(R.id.textViewProductDetail2);
//        qtyValue = findViewById(R.id.qty_value);
//        addQty = findViewById(R.id.add_qty);
//        minusQty = findViewById(R.id.qty_minus);
//        totalTxt = findViewById(R.id.total);




        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String status = getIntent().getStringExtra("status");
//        int id = getIntent().getIntExtra("id", 0);
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
//
//// set the product information in the TextViews
//        label.setText(product.getName());
////        productPrice.setText(String.valueOf(product.getPrice()));


//        // get product ID from Intent
//        int productId = getIntent().getIntExtra("product_id", -1);
//
//        // TODO: use the product ID to fetch the product details from the database or API
//
//        // display the product details
//        TextView productName = findViewById(R.id.label);
//        TextView productPrice = findViewById(R.id.price);
//        ImageView productImage = findViewById(R.id.image);
//
//        // TODO: set the product information in the TextViews and ImageView

//        ------------------------------------------------
//        String n = getIntent().getStringExtra("PName").toString();
//        p = getIntent().getIntExtra("PPrice", 0);
//        q = getIntent().getIntExtra("PQty", 0);
//
//        name.setText(n);
//        price.setText("Price: "+p);
//        totalTxt.setText("Total: "+p);

//        addQty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int qValue = Integer.parseInt(qtyValue.getText().toString());
//                ++qValue;
//                if(qValue<=q) {
//                    qtyValue.setText(Integer.toString(qValue));
//                    int tot = p * qValue;
//                    totalTxt.setText("Total: "+ tot);
//                }
//            }
//        });
//
//        minusQty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int qValue = Integer.parseInt(qtyValue.getText().toString());
//                --qValue;
//                if(qValue>0) {
//                    qtyValue.setText(Integer.toString(qValue));
//                    int tot = p * qValue;
//                    totalTxt.setText("Total: "+ tot);
//                }
//            }
//        });
    }


    public void clickAdd(View view) {
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