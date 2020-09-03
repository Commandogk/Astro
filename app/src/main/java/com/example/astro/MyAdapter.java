package com.example.astro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<String> titles;
    List<String> descriptions;
    List<String> nasa_ids;
    Context mcontext;
    public MyAdapter(Context context, List<String> titles1, List<String> descriptions1, List<String> nasa_ids1) {
        titles = new ArrayList<>(titles1);
        descriptions = new ArrayList<>(descriptions1);
        nasa_ids = new ArrayList<>(nasa_ids1);
        mcontext = context;

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(titles.get(position));
        holder.desc.setText(descriptions.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, imagedis.class);
                intent.putExtra("id", nasa_ids.get(position));
                intent.putExtra("title", titles.get(position));
                intent.putExtra("desc", descriptions.get(position));
                mcontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemtitle);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}

