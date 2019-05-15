package com.example.movieomdbapp.api;

import com.example.movieomdbapp.model.Filme;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("?t={name}&plot=full&apikey=dfc3c601")
    Call<Filme> recuperarFilme(@Query("name") String name);
}
