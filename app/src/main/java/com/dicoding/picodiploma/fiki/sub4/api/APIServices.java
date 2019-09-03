package com.dicoding.picodiploma.fiki.sub4.api;

import com.dicoding.picodiploma.fiki.sub4.model.MoviesResponse;
import com.dicoding.picodiploma.fiki.sub4.model.TvShowsResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("discover/movie")
    Call<MoviesResponse> getListMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowsResponse> getListTvShows(@Query("api_key") String apiKey);
}
