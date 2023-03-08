package com.example.prm_app_shopping.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_app_shopping.model.Cart;

import java.util.ArrayList;

public class CartAdapter {

    Context context;
    ArrayList<Cart> carts;

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }
}
