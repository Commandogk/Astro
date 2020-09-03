package com.example.astro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailes extends YouTubeBaseActivity  {
    TextView d, e, t;
    String date;
    ImageView imageView;
    YouTubePlayerView video;
    String key = "AIzaSyDERAkCy-5d5C5rPjTK_LmvZfs1SoQKFKY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        Button back = findViewById(R.id.back);
        d = findViewById(R.id.datw);
        e = findViewById(R.id.body);
        t = findViewById(R.id.title2);
       imageView = findViewById(R.id.img);
        Intent intent = getIntent();
        video =  findViewById(R.id.utube);
        date = intent.getStringExtra("Date");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        json jsonPlaceHolderApi = retrofit.create(json.class);
        Call<Apod> call = jsonPlaceHolderApi.getapod(date, false, "ehjfIXzMjXJ1xxc6WgOy2xz2VhJ80mj5z7SVFS5y");
        call.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                if (!response.isSuccessful()) {
                    e.setText("Code: " + response.code());
                    return;
                }
                Apod posts = response.body();
                String d1 = posts.getDate();
                String expl = posts.getExplanation();
                String t1 = posts.getTitle();
                if (posts.getMedia_type().equals("image")) {
                    String url = posts.getUrl();
                    setimage(url);
                } else if (posts.getMedia_type().equals("video")) {
                    String url = posts.getUrl();
                   playvideo(url);
                }
                e.setText(expl);
                t.setText(t1);
                d.setText(d1);
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {
                e.setText(t.getMessage());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailes.this, firstpage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void playvideo( String url) {
       imageView.setVisibility(View.INVISIBLE);
       final String ukey;
       int p = url.lastIndexOf('/');
       int q=url.lastIndexOf("?");
       if(q>p)
       ukey=url.substring(p+1,q);
       else
           ukey=url.substring(p+1);
       
        video.setVisibility(View.VISIBLE);
        video.initialize(key, new YouTubePlayer.OnInitializedListener() {
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(ukey);
            }

            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void setimage(String a) {
        imageView.setVisibility(View.VISIBLE);
        video.setVisibility(View.INVISIBLE);
        Glide.with(this).load(a).into(imageView);
    }
}