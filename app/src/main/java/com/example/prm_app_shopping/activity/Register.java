package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prm_app_shopping.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Register extends AppCompatActivity {

    private EditText Fullname, Date, Username, Password, ConfirmPassword, Phone, Email ;
    androidx.appcompat.widget.AppCompatButton Submit, Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Fullname = findViewById(R.id.fullname);
        Date = findViewById(R.id.date);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Phone = findViewById(R.id.phone);
        Email = findViewById(R.id.email);
        ConfirmPassword = findViewById(R.id.confirmPassword);

        Submit = findViewById(R.id.submit);
//        Cancel = findViewById(R.id.cancel);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {checkDataEntered();}
        }) ;
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });

        Cancel = (AppCompatButton) findViewById(R.id.cancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }

    private void checkDataEntered() {
        String inputUserName = Username.getText().toString();
        String inputPassword = Password.getText().toString();
        String confirmPw = ConfirmPassword.getText().toString();
        String inputPhone = Phone.getText().toString();
        String inputFullName = Fullname.getText().toString();
        String birthdate = Date.getText().toString();

        if (inputUserName.isEmpty() || !inputUserName.matches("^[a-zA-Z0-9]*$")) {
            showError(Username, "Username not only contain digits and letter");
        }
        else if (birthdate.isEmpty()) {
            showError(Date, "Birthdate is required");
        }
        else if (inputFullName.isEmpty()) {
            showError(Fullname, "FullName is required");
        }else if (inputPassword.isEmpty() || inputPassword.length() < 8) {
            showError(Password, "password must at least 7 characters");
        } else if (!confirmPw.equals(inputPassword)) {
            showError(ConfirmPassword, "password not match");
        } else if (inputPhone.length() != 10) {
            showError(Phone, "Invalid Phone, must be 10 number");
        } else if (!isEmail(Email)) {
            showError(Email, "Invalid email");
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = new Date();
            try {
                date = sdf.parse(birthdate);
            } catch (ParseException e) {
                e.printStackTrace();
                showError(Date,"invalid");
            }
            Calendar currentDate = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();
            birthDate.setTime(date);
            int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
            if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }
            if (age < 18) {
                showError(Date, "Age must be over 18");
            } else {
                Toast t = Toast.makeText(this, "Register successfully !!!", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
    private void datePicker() {
        Calendar dob = Calendar.getInstance();
        int day = dob.get(Calendar.DATE);
        int month = dob.get(Calendar.MONTH);
        int year = dob.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                dob.set(day, month, year);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                simpleDateFormat.setLenient(false);
                Date.setText(simpleDateFormat.format(dob.getTime()));
            }
        }, day, month, year);
        datePickerDialog.show();
    }
}