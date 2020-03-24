package com.example.bookstore.network.services;

import com.example.bookstore.network.models.Book;
import com.example.bookstore.network.models.SignForm;
import com.example.bookstore.network.models.Token;
import com.example.bookstore.network.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.example.bookstore.network.api.APIConstants.SERVICE_BOOKS;
import static com.example.bookstore.network.api.APIConstants.SERVICE_LOGIN;
import static com.example.bookstore.network.api.APIConstants.SERVICE_REGISTER;
import static com.example.bookstore.network.api.APIConstants.SERVICE_SLIDER;
import static com.example.bookstore.network.api.APIConstants.SERVICE_USER_PROFILE;

public interface APIService {


    @GET(SERVICE_SLIDER)
    Call<List<String>> getSlider();

    @GET(SERVICE_BOOKS)
    Call<List<Book>>getBooks();

    @POST(SERVICE_LOGIN)
    Call<Token> login (@Body SignForm signForm);

    @POST(SERVICE_REGISTER)
    Call<Token> register (@Body SignForm signForm);

    @GET(SERVICE_USER_PROFILE)
    Call<User> getProfile(@Header("x-auth-token") String accessToken);
}
