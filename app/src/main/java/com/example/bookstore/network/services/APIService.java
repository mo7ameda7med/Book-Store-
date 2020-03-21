package com.example.bookstore.network.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.bookstore.network.api.APIConstants.SERVICE_SLIDER;

public interface APIService {


    @GET(SERVICE_SLIDER)
    Call<List<String>> getSlider();
}
