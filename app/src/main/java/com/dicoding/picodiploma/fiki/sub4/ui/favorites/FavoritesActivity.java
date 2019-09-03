package com.dicoding.picodiploma.fiki.sub4.ui.favorites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dicoding.picodiploma.fiki.sub4.MainActivity;
import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.adapter.ViewPagerAdapter;
import com.dicoding.picodiploma.fiki.sub4.db.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class FavoritesActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    public static FavoritesActivity ma;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title_favorites_movie_tv));
        TabLayout tabLayout = findViewById(R.id.tab_layout_id);
        ViewPager viewPager = findViewById(R.id.view_pager_id);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        String moviesTitle = getResources().getString(R.string.movies_title);
        String tvShowsTitle = getResources().getString(R.string.tv_show_title);
        ma = this;
        databaseHelper = new DatabaseHelper(this);

        if(savedInstanceState == null){
            // ada koneksi internet
            //menambahkan fragment
            viewPagerAdapter.addFragment(new MoviesFavoriteFragment(), moviesTitle);
            viewPagerAdapter.addFragment(new TvshowsFavoriteFragment(), tvShowsTitle);

            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);

            //menambahkan icon pada tab layout
            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_movie_black_24dp);
            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_live_tv_black_24dp);

            //menghilangkan shadow dari actionbar
            actionBar = getSupportActionBar();
            Objects.requireNonNull(actionBar).setElevation(0);

            //menambahkan title pada Action Bar
            actionBar.setTitle(getResources().getString(R.string.title_favorites_movie_tv));

        }else {
            //menambahkan fragment
            viewPagerAdapter.addFragment(new MoviesFavoriteFragment(), moviesTitle);
            viewPagerAdapter.addFragment(new TvshowsFavoriteFragment(), tvShowsTitle);

            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);

            //menambahkan icon pada tab layout
            Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_movie_black_24dp);
            Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_live_tv_black_24dp);

            //menghilangkan shadow dari actionbar
            actionBar = getSupportActionBar();
            Objects.requireNonNull(actionBar).setElevation(0);

            //menambahkan title pada Action Bar
            actionBar.setTitle(getResources().getString(R.string.title_favorites_movie_tv));
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}