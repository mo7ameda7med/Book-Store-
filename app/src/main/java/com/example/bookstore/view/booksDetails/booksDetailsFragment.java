package com.example.bookstore.view.booksDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstore.R;
import com.example.bookstore.network.models.Book;
import com.example.bookstore.uitl.PrefManager;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class booksDetailsFragment extends Fragment implements View.OnClickListener{

    private ImageView imageViewBook;
    private TextView tvName;
    private TextView tvNameAuthor;
    private TextView tvPrice;
    private RatingBar ratingBar;
    private TextView tvBook_info;
    private TextView tvAuthor_info;
    private Button btnPlaceOrder;

    private Book book;



    public booksDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_books_details, container, false);
        assert getArguments() != null;
        book=(Book)getArguments().getSerializable("Book");
        initUI(view);
        setupData();
        return view;
    }

    private void initUI(View view)
    {
        imageViewBook=view.findViewById(R.id.book_imageView);
        tvName=view.findViewById(R.id.bookName);
        tvPrice=view.findViewById(R.id.book_price_textView);
        tvNameAuthor=view.findViewById(R.id.book_authorName_tv);
        ratingBar=view.findViewById(R.id.book_ratingBar);
        tvAuthor_info=view.findViewById(R.id.book_author_info_textView);
        tvBook_info=view.findViewById(R.id.book_info_textView);
        btnPlaceOrder=view.findViewById(R.id.book_place_order_button);
        btnPlaceOrder.setOnClickListener(this);
    }
    private void setupData()
    {
        Picasso.get().
                load(book.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imageViewBook);

        tvName.setText(book.getName());
        tvPrice.setText(String.valueOf(book.getPrice()));
        tvNameAuthor.setText(book.getAuthor());
        ratingBar.setRating(book.getRating());
        tvBook_info.setText(book.getBookDescription());
        tvAuthor_info.setText(book.getAboutAuthor());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.book_place_order_button:
                if(PrefManager.retrieveAccessToken(Objects.requireNonNull(getActivity()))!=null)
                {

                }
                else
                {
                    Toast.makeText(getActivity(), "You should login first", Toast.LENGTH_SHORT).show();
                }
        }

    }
}
