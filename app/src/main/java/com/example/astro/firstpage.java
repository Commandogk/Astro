package com.example.astro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Date;

public class firstpage extends AppCompatActivity {
   private String date;
   DatePicker datePicker;
   Button button,bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        datePicker=findViewById(R.id.datepicker);
        button=findViewById(R.id.bt);
        bck=findViewById(R.id.ba);
        datePicker.setMaxDate(new Date().getTime());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y,m,d;
                y=datePicker.getYear();
                m=datePicker.getMonth()+1;
                d=datePicker.getDayOfMonth();
                if(m<10&&d<10)
                date=String.valueOf(y)+"-0"+String.valueOf(m)+"-0"+String.valueOf(d);
                else if(m<10)
                date=String.valueOf(y)+"-0"+String.valueOf(m)+"-"+String.valueOf(d);
                else if(d<10)
                    date=String.valueOf(y)+"-"+String.valueOf(m)+"-0"+String.valueOf(d);
                else
                    date=String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d);
               Intent intent=new Intent(firstpage.this,detailes.class);
               intent.putExtra("Date",date);
               startActivity(intent);
            }
        });
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(firstpage.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}