package com.dicoding.picodiploma.fiki.sub4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.picodiploma.fiki.sub4.adapter.MoviesAdapter;
import com.dicoding.picodiploma.fiki.sub4.api.APIServices;
import com.dicoding.picodiploma.fiki.sub4.model.Movies;
import com.dicoding.picodiploma.fiki.sub4.model.MoviesResponse;
import com.dicoding.picodiploma.fiki.sub4.ui.favorites.FavoritesActivity;
import com.dicoding.picodiploma.fiki.sub4.ui.favorites.MoviesFavoriteFragment;
import com.dicoding.picodiploma.fiki.sub4.ui.favorites.TvshowsFavoriteFragment;
import com.dicoding.picodiploma.fiki.sub4.ui.movies.MoviesFragment;
import com.dicoding.picodiploma.fiki.sub4.ui.tvshows.TvshowsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;

    final Fragment fragment1 = new MoviesFragment();
    final Fragment fragment2 = new TvshowsFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;



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



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment1, "1").commit();

//
//        if(savedInstanceState != null){
//            setContentView(R.layout.activity_main);
//            navView = findViewById(R.id.nav_view);
//            int selectedItemId = savedInstanceState.getInt("SelectedItemId");
//            navView.setSelectedItemId(selectedItemId);
//            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//            ActionBar actionBar = getSupportActionBar();
//            actionBar.setTitle(getResources().getString(R.string.app_name));
//        }else {
//            setContentView(R.layout.activity_main);
//            MoviesFragment fragment = new MoviesFragment();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.nav_host_fragment, fragment, getClass().getSimpleName())
//                    .commit();
//            navView = findViewById(R.id.nav_view);
//            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//            ActionBar actionBar = getSupportActionBar();
//            actionBar.setTitle(getResources().getString(R.string.app_name));
//        }

    }
//
//
//    private void initMovie(){
//
//    }
//
//    private void connectingAndGetAPIData(){
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        APIServices APIServices = retrofit.create(APIServices.class);
//        Call<MoviesResponse> call = APIServices.getListMovies(API_KEY);
//        showLoading(true);
//        call.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                try{
//                    showLoading(false);
//                    movies = response.body().getResults();
//                    GridLayoutManager mLayoutManager  = new GridLayoutManager(getActivity(),2);
//                    rvMovie.setLayoutManager(mLayoutManager);
//                    mMoviesAdapter = new MoviesAdapter(movies);
//                    rvMovie.setAdapter(mMoviesAdapter);
//
//                    Log.d(TAG, "Number of movies received: " + movies.size());
//                }catch (Exception e){
//                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                Log.e(TAG, t.toString());
//            }
//        });
//
//    }
//

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int selectedItemId = savedInstanceState.getInt("SelectedItemId");
        navView.setSelectedItemId(selectedItemId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SelectedItemId", navView.getSelectedItemId());
    }


}
