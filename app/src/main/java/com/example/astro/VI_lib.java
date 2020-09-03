package com.example.astro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VI_lib extends AppCompatActivity {
    String s;
    json q;
    Button b;
    EditText search;
    List<String> titles = new ArrayList<>();
    List<String> descriptions = new ArrayList<>();
    List<String> nasa_ids = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_i_lib);
        search = findViewById(R.id.search);
        b=findViewById(R.id.bck);
        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         search.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                 s = editable.toString();
                 getlist(s);

             }
         });
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent a= new Intent(VI_lib.this,MainActivity2.class);
                 startActivity(a);
                 finish();
             }
         });
    }

    private void getlist(String s) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://images-api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        q = retrofit.create(json.class);
        Call<collectiond> call =q.getlistdata(s);
        call.enqueue(new Callback<collectiond>() {
            @Override
            public void onResponse(Call<collectiond> call, Response<collectiond> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(VI_lib.this, "error", Toast.LENGTH_LONG).show();
                    return;
                }
                collectiond collectiond = response.body();
                titles.clear();
                descriptions.clear();
                nasa_ids.clear();
                assert collectiond !=null;
                for(int i =0 ;i<collectiond.getCollectio().getItem().size();i++){
                    titles.add(collectiond.getCollectio().getItem().get(i).getData().get(0).getTitle());
                    String des = collectiond.getCollectio().getItem().get(i).getData().get(0).getDescription();
                    if(des!=null) {
                        descriptions.add(des);
                    }
                    else{
                        descriptions.add("-------");
                    }
                    nasa_ids.add(collectiond.getCollectio().getItem().get(i).getData().get(0).getNasa_id());
                }
                MyAdapter myadapter = new MyAdapter(VI_lib.this,titles,descriptions,nasa_ids);
                recyclerView.setAdapter(myadapter);


            }

            @Override
            public void onFailure(Call<collectiond> call, Throwable t) {

            }
        });
    }
}