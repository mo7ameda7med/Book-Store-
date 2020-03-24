package com.example.bookstore.view.home.Books;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.example.bookstore.network.models.Book;
import com.squareup.picasso.Picasso;

public class BooksHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvOldPrice;
    private TextView tvFree;
    private View tvHasDiscount;

    //feature
    private RatingBar ratingBar;

    private BookTypes bookTypes;


    public BooksHolder(@NonNull View itemView ,BookTypes bookTypes) {
        super(itemView);
        this.bookTypes=bookTypes;
        initView(itemView);
    }

    private void initView(View view)
    {
        imageView=view.findViewById(R.id.book_imageView);
        tvName=view.findViewById(R.id.bookName);
        tvPrice=view.findViewById(R.id.book_price_textView);
        tvOldPrice=view.findViewById(R.id.book_old_price_textView);
        tvFree=view.findViewById(R.id.book_free_textView);
        tvHasDiscount=view.findViewById(R.id.has_discount_group);

        if(bookTypes==BookTypes.Featured)
        {
            ratingBar=view.findViewById(R.id.book_ratingBar);
        }
    }
     void BindV(Book book)
    {
        Picasso.get()
                .load(book.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        tvName.setText(book.getName());
        if(book.getPrice()==0 && book.getDiscount()==0)
        {
            tvFree.setVisibility(View.VISIBLE);
            tvPrice.setVisibility(View.GONE);
            tvHasDiscount.setVisibility(View.GONE);
        }
        else
        {
            tvFree.setVisibility(View.GONE);
            tvPrice.setVisibility(View.VISIBLE);

            if(book.getDiscount() !=0)
            {
                tvHasDiscount.setVisibility(View.VISIBLE);
                tvOldPrice.setText(String.valueOf(book.getDiscount()));

                double discount = (book.getPrice() - (book.getPrice() * ((double) book.getDiscount() / 100)));
                tvPrice.setText(String.valueOf(discount));
            }
            else
            {
                tvHasDiscount.setVisibility(View.GONE);
                tvPrice.setText(String.valueOf(book.getPrice()));

            }
        }

        if(bookTypes==BookTypes.Featured)
        {
            ratingBar.setRating(book.getRating());
        }
    }


}
