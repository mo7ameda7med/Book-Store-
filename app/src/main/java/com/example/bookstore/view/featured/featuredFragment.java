package com.example.bookstore.view.featured;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookstore.R;
import com.example.bookstore.network.api.APIClient;
import com.example.bookstore.network.models.Book;
import com.example.bookstore.network.services.APIService;
import com.example.bookstore.view.home.Books.BookTypes;
import com.example.bookstore.view.home.Books.BooksAdapter;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class featuredFragment extends Fragment {

    private RecyclerView recyclerView;
    private APIService apiService;
    private BooksAdapter  booksAdapter;

    public featuredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_featured, container, false);
        apiService= APIClient.getClient().create(APIService.class);
        initUI(view);
        getBooks();
        return view;
    }

    private void initUI(View view){
        recyclerView=view.findViewById(R.id.recyclerView);
    }
    private void getBooks()
    {
        apiService.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    setupBooks(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setupBooks(List<Book> book)
    {
        booksAdapter=new BooksAdapter(book, BookTypes.Featured);

        LinearLayoutManager linearLayout=new LinearLayoutManager(Objects.requireNonNull(getActivity())
                .getApplicationContext(),
                LinearLayoutManager.VERTICAL
                ,false);

        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(booksAdapter);

    }
}
