package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FireBaseStore extends AppCompatActivity {
FirebaseAuth fbauth=FirebaseAuth.getInstance();
EditText name,age;

    FirebaseFirestore db =FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_store);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        Map<String, Object> studentData=new HashMap<>();
        studentData.put("Name",name.getText().toString());
        studentData.put("Age",Integer.parseInt(age.getText().toString()));
        CollectionReference rdb=db.collection("BSITF20");
        DocumentReference ddb=rdb.document("aymen");
        ddb.set(studentData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sign up not successful!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}