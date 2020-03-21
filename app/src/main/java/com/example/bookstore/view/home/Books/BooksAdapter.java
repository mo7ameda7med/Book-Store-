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

    public BooksAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ietm_books,parent,false);
        return new BooksHolder(v);
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
