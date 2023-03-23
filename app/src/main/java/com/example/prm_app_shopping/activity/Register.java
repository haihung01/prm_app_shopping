package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prm_app_shopping.R;
import com.example.prm_app_shopping.api.UsersApiService;
import com.example.prm_app_shopping.model.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {
    TextView firstname, lastname, phone, email, password, confPassword;
    AppCompatButton submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname = findViewById(R.id.username);
        lastname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confPassword = findViewById(R.id.confirmPassword);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);

        Users customer = new Users("", String.valueOf(password.getText()), String.valueOf(phone.getText()), String.valueOf(email.getText()), "", "", "", "", String.valueOf(firstname.getText()), String.valueOf(lastname.getText()));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!String.valueOf(password.getText()).equals(String.valueOf(confPassword.getText()))) {
                    showWrongPasswordAlert("Password phải giống với confirmPassword");
                } else {
                    createUsers(customer);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createUsers(Users user) {
        ArrayList<Users> listUsers = new ArrayList<>();

        UsersApiService.UsersApiService.allUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> allUsers = response.body();
                listUsers.addAll(allUsers);
                for (Users use : listUsers
                ) {
                    if (user.getEmail().equals(use.getEmail())) {
                        showWrongPasswordAlert("Email đã được sử dụng");
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                showWrongPasswordAlert("không thể kiểm tra được thông tin người dùng, vui lòng kiểm tra các kết nối");
            }
        });
        user.setId(String.valueOf(listUsers.size() + 1));

        Call<Users> call = UsersApiService.UsersApiService.createUsers(user);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                // Xử lý kết quả thành công
                Users user = response.body();
                // ...
//                showWrongPasswordAlert("Đăng ký thành công.");
//                Log.d("log-note","register success");
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Xử lý lỗi
                showWrongPasswordAlert("Đã có lỗi xảy ra");
//                Log.d("log-note","ERRoR");
            }
        });
    }

    private void showWrongPasswordAlert(String mess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(mess);
        builder.setPositiveButton("Close", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}