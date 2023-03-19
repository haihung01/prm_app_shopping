package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.prm_app_shopping.R;
import com.google.android.material.navigation.NavigationView;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class ProductListActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Button viewMoreButton;
    private Menu navButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

//        viewMoreButton = findViewById(R.id.view_more_button);
        drawerLayout = findViewById(R.id.drawer_layout_product_list);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.login: {
                    drawerLayout.close();
                    startActivity(new Intent(ProductListActivity.this, Login.class));
                    return true;
                }
                case R.id.home: {
                    drawerLayout.close();
                    startActivity(new Intent(ProductListActivity.this, MainActivity.class));
                    finish();
                    return true;
                }
//                case R.id.order_history: {
//                    drawerLayout.close();
//                    startActivity((new Intent(ProductListActivity.this, Hi.class)));
//                    return true;
//                }
            }
            return true;
        });


        // Find the MaterialSearchBar
//        Menu searchBar = findViewById(R.id.searchBar);
//
//// Find the button inside the MaterialSearchBar
//        ImageButton navButton = searchBar.findViewById(R.id.mt_nav);
//        navButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open the Sidebar
//                DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });


    }
}