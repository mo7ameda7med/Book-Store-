package com.example.bookstore.view.home.Books;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.example.bookstore.network.models.Book;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksHolder> {
    private List<Book>books;
    public BookTypes bookTypes;

    public BooksAdapter(List<Book> books, BookTypes bookTypes) {
        this.books = books;
        this.bookTypes = bookTypes;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int Layout =0;
        switch (bookTypes)
        {
            case Home:
               Layout= R.layout.ietm_books;
            break;
            case Featured:
            case MY_Books:
               Layout= R.layout.item_book_land;
                break;
        }
        View v= LayoutInflater.from(parent.getContext()).inflate(Layout,parent,false);
        return new BooksHolder(v,bookTypes);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {
        Book book=books.get(position);
        holder.BindV(book);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
