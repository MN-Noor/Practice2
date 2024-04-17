package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
Button signup;
TextView signin;
EditText emailEditText,passwordEditText;

FirebaseAuth Fbauth=FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.pass);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if email and password are not empty
                if (!email.isEmpty() && !password.isEmpty()) {
                    // Create user with email and password
                    Fbauth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign up success
                                        Toast.makeText(getApplicationContext(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Display a message if email or password is empty
                    Toast.makeText(getApplicationContext(), "Please enter email and password.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
             public void onClick(View v){
                Intent intent =new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

}
