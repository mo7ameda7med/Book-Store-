package com.example.bookstore.view.home.slider;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.squareup.picasso.Picasso;

public class sliderHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    public sliderHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View view)
    {
        imageView=view.findViewById(R.id.sliderImage);
    }

    void BindView(String image)
    {
        Picasso.get().load(image)
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);
    }
}
