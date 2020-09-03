package com.example.astro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
   Button apod, lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        apod=findViewById(R.id.apod);
        lv=findViewById(R.id.IV);
        apod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(MainActivity2.this,firstpage.class);
                startActivity(a);
                finish();
            }
        });
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(MainActivity2.this,VI_lib.class);
                startActivity(a);
                finish();
            }
        });
    }
}