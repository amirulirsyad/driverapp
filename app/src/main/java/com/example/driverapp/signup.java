package com.example.driverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText fullname, email, password, passwordconfirm;
    final int MIN_PASSWORD_LENGTH = 6;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        viewInitializations();
    }

    void viewInitializations() {
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordconfirm = findViewById(R.id.passwordconfirm);

        // To show back button in actionbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    boolean validateInput()
    {
        if (fullname.getText().toString().equals("")) {
            fullname.setError("Please Enter Full Name");
            return false;
        }
        if (email.getText().toString().equals("")) {
            email.setError("Please Enter Email");
            return false;
        }
        if (password.getText().toString().equals("")) {
            password.setError("Please Enter Password");
            return false;
        }
        if (passwordconfirm.getText().toString().equals("")) {
            passwordconfirm.setError("Please Enter Repeat Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(email.getText().toString())) {
            email.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (password.getText().length() < MIN_PASSWORD_LENGTH) {
            password.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }

        // Checking if repeat password is same
        if (!password.getText().toString().equals(passwordconfirm.getText().toString())) {
            passwordconfirm.setError("Password does not match");
            return false;
        }
        return true;
    }
    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void performSignUp (View v){
        if (validateInput()) {
            String Fullname = fullname.getText().toString();
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            String repeatPassword = passwordconfirm.getText().toString();

            Toast.makeText(this,"Register Success", Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                            }
                        }
                    });
        }
    }
}