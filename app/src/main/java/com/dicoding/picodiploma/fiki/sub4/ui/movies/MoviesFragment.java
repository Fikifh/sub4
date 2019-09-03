package com.dicoding.picodiploma.fiki.sub4.ui.movies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.picodiploma.fiki.sub4.MainActivity;
import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.adapter.MoviesAdapter;
import com.dicoding.picodiploma.fiki.sub4.api.APIServices;
import com.dicoding.picodiploma.fiki.sub4.model.Movies;
import com.dicoding.picodiploma.fiki.sub4.model.MoviesResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesFragment extends Fragment {

    private RecyclerView rvMovie;
    private ArrayList<Movies> movies = new ArrayList<>();
    private ArrayList<Movies> movieState;
    private ProgressBar progressBar;
    private GridLayoutManager mLayoutManager;
    private MoviesAdapter mMoviesAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "67b5496e1b4c26ad30c16ad08f2deaa2";
    private  final static String MOVIE_KEY = "SI_KEY";

    public MoviesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        progressBar = v.findViewById(R.id.progressBar);
        rvMovie = v.findViewById(R.id.rv_movies_id);
        rvMovie.setHasFixedSize(true);
        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList(MOVIE_KEY);
            Toast.makeText(getActivity(), movies.toString(), Toast.LENGTH_LONG).show();
            mLayoutManager  = new GridLayoutManager(getActivity(),2);
            rvMovie.setLayoutManager(mLayoutManager);
            mMoviesAdapter = new MoviesAdapter(movies);
            rvMovie.setAdapter(mMoviesAdapter);
        } else {
            connectingAndGetAPIData();
            Toast.makeText(getActivity(), "SAVE STATE NULL", Toast.LENGTH_LONG).show();
        }
        return v;
    }


    public void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void connectingAndGetAPIData(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        APIServices APIServices = retrofit.create(APIServices.class);
        Call<MoviesResponse> call = APIServices.getListMovies(API_KEY);
        showLoading(true);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                try{
                    showLoading(false);
                    movies = response.body().getResults();
                    GridLayoutManager mLayoutManager  = new GridLayoutManager(getActivity(),2);
                    rvMovie.setLayoutManager(mLayoutManager);
                    mMoviesAdapter = new MoviesAdapter(movies);
                    rvMovie.setAdapter(mMoviesAdapter);
                    Log.d(TAG, "Number of movies received: " + movies.size());
                }catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(MOVIE_KEY, movies);
        super.onSaveInstanceState(outState);
    }

}