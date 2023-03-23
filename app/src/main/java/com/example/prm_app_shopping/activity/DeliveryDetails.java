package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.adapters.CartAdapter;
import com.example.prm_app_shopping.databinding.ActivityCardBinding;
import com.example.prm_app_shopping.databinding.ActivityDeliveryDetailsBinding;
import com.example.prm_app_shopping.model.Cart;
import com.example.prm_app_shopping.model.Orders;
import com.example.prm_app_shopping.model.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class DeliveryDetails extends AppCompatActivity {

    ArrayList<Cart> carts;
    CartAdapter CartAdapter;
    ActivityDeliveryDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeliveryDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("CACHE", MODE_PRIVATE);
        String cachedUsers = sharedPreferences.getString("USERS", null);
        Users customer = new Gson().fromJson(cachedUsers, Users.class);

        carts = new ArrayList<>();
        String json = sharedPreferences.getString("cartsList", null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Cart>>() {
            }.getType();
            carts = gson.fromJson(json, type);
        }

        CartAdapter = new CartAdapter(this, carts);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        binding.orderItems.setLayoutManager(layoutManager);
        binding.orderItems.setAdapter(CartAdapter);
        EditText editText = findViewById(R.id.edit_text);

// Thiết lập sự kiện khi EditText được chạm vào
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày giờ hiện tại để đặt làm ngày mặc định cho DatePickerDialog
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Khởi tạo và hiển thị DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryDetails.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Lưu ngày được chọn vào một đối tượng Calendar
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, monthOfYear, dayOfMonth);

                                // Lấy giá trị của EditText và xử lý
                                String userInput = editText.getText().toString();
                                // TODO: Xử lý giá trị được nhập từ người dùng

                                // Định dạng ngày giờ và hiển thị trên EditText
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        String selectedDate = sdf.format(selectedCalendar.getTime());
                                        editText.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                editText.setText(selectedDate);
                                            }
                                        });
                                    }
                                }).start();
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        binding.finalcheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orders oder = new Orders(generateRandomString(8),String.valueOf(customer.getId()),carts,0,null,null,null);
                Log.d("log-note",oder.toString());
            }
        });
    }
        private static String generateRandomString(int length) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder(length);
            Random random = new Random();

            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(characters.length());
                char randomChar = characters.charAt(randomIndex);
                sb.append(randomChar);
            }

            return sb.toString();
        }

}