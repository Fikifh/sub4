package com.dicoding.picodiploma.fiki.sub4.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Icon;
import android.provider.Settings;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.picodiploma.fiki.sub4.MainActivity;
import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.db.DatabaseHelper;
import com.dicoding.picodiploma.fiki.sub4.db.MovieModel;
import com.dicoding.picodiploma.fiki.sub4.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class MoviesDetailActivity extends AppCompatActivity {
    private static final String IMAGE_URL_BASE_PATH= "https://image.tmdb.org/t/p/w185/";
    private ProgressBar progressBar;
    private ImageButton imgBtn;
    Movies movie;
    DatabaseHelper databaseHelper;
    ArrayList<MovieModel> arrayList = new ArrayList<>();
    TextView tvTitle;
    TextView tvDesc;
    ImageView ivMovie;
    TextView tvTahun;
    String image_url;
    int idMovie;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_detail_activity);
        databaseHelper = new DatabaseHelper(this);
        initView();
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

    private void initView() {
        TextView tvTahunTrans = findViewById(R.id.textViewtahun_tvshow);
        TextView tvDescTrans = findViewById(R.id.textViewDesc_tvshow);
        progressBar = findViewById(R.id.progressbarDetailMovie);
        imgBtn = findViewById(R.id.imageButton);

        tvTahunTrans.setText(getResources().getString(R.string.yeartext));
        tvDescTrans.setText(getResources().getString(R.string.deskripsi_movie));

        tvTitle = findViewById(R.id.tv_title_movies);
        tvDesc = findViewById(R.id.tv_desc_movies);
        ivMovie = findViewById(R.id.imageView_movies);
        tvTahun = findViewById(R.id.tv_tahun);

        while (movie==null){
            showLoading(true);
            movie= Objects.requireNonNull(getIntent().getExtras()).getParcelable("EXTRAS");
        }
        String title = Objects.requireNonNull(movie).getTitle();
        String description = movie.getOverview();
        String tahun = movie.getReleaseDate();
        image_url = IMAGE_URL_BASE_PATH + movie.getPosterPath();
        idMovie = movie.getId();

        tvTitle.setText(title);
        tvDesc.setText(description);
        tvTahun.setText(tahun);
        Picasso.get().load(image_url).into(ivMovie);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        clickFavorite();
    }

    private void clickFavorite(){
        Cursor cursor = null;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM movie WHERE id_movie="+idMovie+"", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            imgBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    try {
                        db.execSQL("DELETE FROM movie WHERE id_movie ="+idMovie+"");
                        imgBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                        reIntent();
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            imgBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    String str = tvDesc.getText().toString();
                    String desc = str.replaceAll("'", "");
                    String strTitle = tvTitle.getText().toString();
                    String title = strTitle.replaceAll("'", "");
                    try {
                        db.execSQL("insert into movie(id_movie,title_movie,img_movie, desc_movie, date_movie)" +
                                "values(" + idMovie + ",'" +
                                 title + "','" +
                                image_url + "','" +
                                 desc + "','" +
                                tvTahun.getText().toString() + "')");
                        imgBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
                        Toast.makeText(getApplicationContext(), "Favorite", Toast.LENGTH_LONG).show();
                        reIntent();
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    public void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void reIntent(){
        Intent intent = new Intent(getApplicationContext(), MoviesDetailActivity.class);
        intent.putExtra("EXTRAS", movie);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
