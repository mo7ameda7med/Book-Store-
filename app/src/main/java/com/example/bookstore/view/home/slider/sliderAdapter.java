package com.example.bookstore.view.home.slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookstore.R;
import com.example.bookstore.view.home.slider.sliderHolder;

import java.util.List;

public class sliderAdapter extends RecyclerView.Adapter<sliderHolder> {

    private List<String>images;

    public sliderAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public sliderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider,parent,false);
        return new sliderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull sliderHolder holder, int position) {
        String image=images.get(position);
        holder.BindView(image);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
