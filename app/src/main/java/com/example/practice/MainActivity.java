package com.example.practice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
Button signin;
TextView signup;
EditText pass,email;
FirebaseAuth fbauth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        signin=findViewById(R.id.button);
        signup=findViewById(R.id.signup);
        Fillin(email,"Already saved info","fill");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String em=email.getText().toString().trim();
              String password=email.getText().toString().trim();
              if(!em.isEmpty() && !password.isEmpty())
              {
              fbauth.signInWithEmailAndPassword(em,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                  @Override
                  public void onComplete(@NotNull Task<AuthResult> task)
                    {
                        if(task.isComplete())
                        {
                            Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"login Failed",Toast.LENGTH_LONG).show();
                        }
                    }

                });
              SaveInfo(em,password);}
              else {
                  Toast.makeText(getApplicationContext(),"dont left emply",Toast.LENGTH_LONG).show();
              }



            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
            Intent intent=new Intent(MainActivity.this,FireBaseStore.class);
            startActivity(intent);
            }
        });


    }


    private void Fillin(View view,String message,String actionText)
    {
        Snackbar snackbar=Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        snackbar.setAction(actionText,new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("coll",Context.MODE_PRIVATE);
                email.setText(sharedPreferences.getString("email_key","email"));
                pass.setText(sharedPreferences.getString("pass_key","pass"));
            }
        });
        snackbar.show();
    }
    private void SaveInfo(String email,String pass){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Remember").setMessage("Save Credentials for later use");
        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences=getSharedPreferences("coll", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("email_key",email);
                editor.putString("pass_key",pass);
                editor.apply();
            }
        }).setNegativeButton("Later",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                Toast toast=Toast.makeText(getApplicationContext(),"not saved",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }

}