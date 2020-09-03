package com.example.astro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class imagedis extends AppCompatActivity {
    TextView title , description;
    ImageView disp;
    Button bc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagedis);
        Intent intent = getIntent();
        final String title1 = intent.getStringExtra("title");
        final String desc = intent.getStringExtra("desc");
        String nasa_id = intent.getStringExtra("id");
        bc =findViewById(R.id.bck1);
        title = findViewById(R.id.title3);
        description = findViewById(R.id.ddesc);
        disp = findViewById(R.id.disimg);
        title.setMovementMethod(new ScrollingMovementMethod());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://images-api.nasa.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        json json1 = retrofit.create(json.class);
        Call<obj> call = json1.getimg(nasa_id);
        
        call.enqueue(new Callback<obj>() {
            @Override
            public void onResponse(Call<obj> call, Response<obj> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(imagedis.this, "error", Toast.LENGTH_LONG).show();
                    return;
                }
                obj obj = response.body();
                int i = 0;
                assert obj != null;
                for(;i<obj.getCollectio().getItem().size() ; i++ ){
                    String x = obj.getCollectio().getItem().get(i).getHref();
                    if(x.endsWith("medium.jpg")){
                        disp.setVisibility(View.VISIBLE);
                        Glide.with(imagedis.this).load(x.replace("http" , "https")).into(disp);
                        break;
                    }
                }
                title.setVisibility(View.VISIBLE);
                description.setVisibility(View.VISIBLE);
                title.setText(title1);
                description.setText(desc);
            }

            @Override
            public void onFailure(Call<obj> call, Throwable t) {
                Toast.makeText(imagedis.this,  t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(imagedis.this,VI_lib.class);
                startActivity(a);
                finish();
            }
        });
    }
}