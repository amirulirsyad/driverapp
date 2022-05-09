package com.example.driverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView email,password;
    Button loginbtn;
    final int MIN_PASSWORD_LENGTH = 6;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        viewIntializations();
    }

    void viewIntializations() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    boolean validateInput() {

        if (email.getText().toString().equals("")) {
            email.setError("Please Enter Email");
            return false;
        }
        if (password.getText().toString().equals("")) {
            password.setError("Please Enter Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(email.getText().toString())) {
            email.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (password.getText().length() < MIN_PASSWORD_LENGTH) {
            password.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + " characters");
            return false;
        }

        return true;

    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void goToSignup(View v) {

        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

        //Button loginbtn = (MaterialButton) findViewById(R.id.loginbtn);


       /* loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFULL",Toast.LENGTH_SHORT).show();
                }else
                    //incorrect
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });*/



}

