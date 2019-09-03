package com.dicoding.picodiploma.fiki.sub4.ui.tvshows;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import com.dicoding.picodiploma.fiki.sub4.adapter.TvShowAdapter;
import com.dicoding.picodiploma.fiki.sub4.api.APIServices;
import com.dicoding.picodiploma.fiki.sub4.model.TvShows;
import com.dicoding.picodiploma.fiki.sub4.model.TvShowsResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvshowsFragment extends Fragment {

    private RecyclerView rvTvShow;
    private ArrayList<TvShows> tvShows = new ArrayList<>();

    private ProgressBar progressBar;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private TvShowAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "67b5496e1b4c26ad30c16ad08f2deaa2";
    private final static String TVSHOW_KEY = "TVSHOW_KEY";

    public TvshowsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tvshows, container, false);
        progressBar = v.findViewById(R.id.progressBarTv);
        rvTvShow = v.findViewById(R.id.rv_tvshows_id);
        rvTvShow.setHasFixedSize(true);

        if(savedInstanceState != null){
            tvShows = savedInstanceState.getParcelableArrayList(TVSHOW_KEY);
            mLayoutManager  = new GridLayoutManager(getActivity(),2);
            rvTvShow.setLayoutManager(mLayoutManager);
            TvShowAdapter mAdapter = new TvShowAdapter(tvShows);
            rvTvShow.setAdapter(mAdapter);
        } else {
            connectingAndGetAPIData();
        }

        return v;

    }


    private void showLoading(Boolean state) {
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
        Call<TvShowsResponse> call = APIServices.getListTvShows(API_KEY);
        showLoading(true);
        call.enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(Call<TvShowsResponse> call, Response<TvShowsResponse> response) {
                try{
                    showLoading(false);
                    tvShows = Objects.requireNonNull(response.body()).getResults();
                    mLayoutManager  = new GridLayoutManager(getActivity(),2);
                    rvTvShow.setLayoutManager(mLayoutManager);

                    mAdapter = new TvShowAdapter(tvShows);
                    rvTvShow.setAdapter(mAdapter);

                    Log.d(TAG, "Number of Tv Shows received: " + tvShows.size());
                }catch (Exception e){
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TvShowsResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(TVSHOW_KEY, tvShows);
        super.onSaveInstanceState(outState);
    }

}