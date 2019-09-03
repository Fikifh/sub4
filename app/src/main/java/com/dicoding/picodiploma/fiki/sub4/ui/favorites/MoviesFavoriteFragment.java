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
import com.dicoding.picodiploma.fiki.sub4.db.DatabaseHelper;
import com.dicoding.picodiploma.fiki.sub4.db.MovieModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFavoriteFragment extends Fragment {


    private RecyclerView rvMovie;

    private ProgressBar progressBar;

    private static final String TAG = MoviesFavoriteFragment.class.getSimpleName();
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    ArrayList<MovieModel> arrayList = new ArrayList<>();
    DatabaseHelper databaseHelper;

    public MoviesFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies_favorite, container, false);
        progressBar = v.findViewById(R.id.progressBarFavoriteMovie);
        rvMovie = v.findViewById(R.id.rv_movie_favorite_id);
        databaseHelper = new DatabaseHelper(getContext());
        getAllMoviesFavorite();
        GridLayoutManager mLayoutManager  = new GridLayoutManager(getContext(),2);
        rvMovie.setLayoutManager(mLayoutManager);
        MoviesFavoriteAdapter mAdapter = new MoviesFavoriteAdapter(arrayList);
        rvMovie.setAdapter(mAdapter);
        Log.d(TAG, "Number of movies received: " + arrayList.size());
        return v;
    }

    public ArrayList<MovieModel> getAllMoviesFavorite() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movie", null);
        cursor.moveToFirst();
        MovieModel movieModel;
        if (cursor.getCount() > 0) {
            do {
                movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id_movie")));
                movieModel.setImg_movie(cursor.getString(cursor.getColumnIndexOrThrow("img_movie")));
                movieModel.setTitle_movie(cursor.getString(cursor.getColumnIndexOrThrow("title_movie")));
                movieModel.setDesc_movie(cursor.getString(cursor.getColumnIndexOrThrow("desc_movie")));
                movieModel.setDate_movie(cursor.getString(cursor.getColumnIndexOrThrow("date_movie")));
                arrayList.add(movieModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
