package com.dicoding.picodiploma.fiki.sub4.ui.favorites;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.adapter.MoviesFavoriteAdapter;
import com.dicoding.picodiploma.fiki.sub4.adapter.TvShowFavoriteAdapter;
import com.dicoding.picodiploma.fiki.sub4.db.DatabaseHelper;
import com.dicoding.picodiploma.fiki.sub4.db.MovieModel;
import com.dicoding.picodiploma.fiki.sub4.db.TvShowModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowsFavoriteFragment extends Fragment {


    private RecyclerView rvTvshow;

    private ProgressBar progressBar;

    private static final String TAG = TvshowsFavoriteFragment.class.getSimpleName();
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    ArrayList<TvShowModel> arrayList = new ArrayList<>();
    DatabaseHelper databaseHelper;


    public TvshowsFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_tvshows_favorite, container, false);
        progressBar = v.findViewById(R.id.progressBarFavoriteTvshow);
        rvTvshow = v.findViewById(R.id.rv_tvshow_favorite_id);
        databaseHelper = new DatabaseHelper(getContext());
        getAllMoviesFavorite();
        GridLayoutManager mLayoutManager  = new GridLayoutManager(getContext(),2);
        rvTvshow.setLayoutManager(mLayoutManager);
        TvShowFavoriteAdapter mAdapter = new TvShowFavoriteAdapter(arrayList);
        rvTvshow.setAdapter(mAdapter);
        Log.d(TAG, "Number of movies received: " + arrayList.size());
        return v;
    }

    public ArrayList<TvShowModel> getAllMoviesFavorite() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tvshow", null);
        cursor.moveToFirst();
        TvShowModel tvshowModel;
        if (cursor.getCount() > 0) {
            do {
                tvshowModel = new TvShowModel();
                tvshowModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id_tvshow")));
                tvshowModel.setImg_tvshow(cursor.getString(cursor.getColumnIndexOrThrow("img_tvshow")));
                tvshowModel.setTitle_tvshow(cursor.getString(cursor.getColumnIndexOrThrow("title_tvshow")));
                tvshowModel.setDesc_tvshow(cursor.getString(cursor.getColumnIndexOrThrow("desc_tvshow")));
                tvshowModel.setDate_tvshow(cursor.getString(cursor.getColumnIndexOrThrow("date_tvshow")));
                arrayList.add(tvshowModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

}
