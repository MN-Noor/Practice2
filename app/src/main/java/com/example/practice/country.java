package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;

public class country extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener {
        String[] country = {"Pakistan", "Afghanistan", "Qatar", "Saudi Arabia", "Palestine"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Spinner spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        ImageView img = findViewById(R.id.image);
        String cont = country[position].toLowerCase().replace(" ","");
        Toast.makeText(getApplicationContext(),cont , Toast.LENGTH_LONG).show();
        int resourceId = getResources().getIdentifier(cont, "drawable", getPackageName());
        img.setImageResource(resourceId);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
