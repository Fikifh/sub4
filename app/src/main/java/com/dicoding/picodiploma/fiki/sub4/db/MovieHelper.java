package com.dicoding.picodiploma.fiki.sub4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.picodiploma.fiki.sub4.model.Movies;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dicoding.picodiploma.fiki.sub4.db.DatabaseContract.MovieColumn.DATE_MOVIE;
import static com.dicoding.picodiploma.fiki.sub4.db.DatabaseContract.MovieColumn.DESC_MOVIE;
import static com.dicoding.picodiploma.fiki.sub4.db.DatabaseContract.MovieColumn.IMG_MOVIE;
import static com.dicoding.picodiploma.fiki.sub4.db.DatabaseContract.MovieColumn.TITLE_MOVIE;
import static com.dicoding.picodiploma.fiki.sub4.db.DatabaseContract.TABLE_MOVIES;

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIES;
    private static DatabaseHelper dataBaseHelper;
    private static MovieHelper INSTANCE;
    private static SQLiteDatabase database;


    public MovieHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getReadableDatabase();
    }

    public void close(){
        dataBaseHelper.close();
        if(database.isOpen())
            database.close();
    }

    public ArrayList<MovieModel> getAllMovies(){
        ArrayList<MovieModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null, null, null, null, _ID+"ASC", null
                );
        cursor.moveToFirst();
        Movies movies;

        if(cursor.getCount() > 0){
            do {
                MovieModel movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movieModel.setTitle_movie(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_MOVIE)));
                movieModel.setImg_movie(cursor.getString(cursor.getColumnIndexOrThrow(IMG_MOVIE)));
                movieModel.setDesc_movie(cursor.getString(cursor.getColumnIndexOrThrow(DESC_MOVIE)));
                movieModel.setDate_movie(cursor.getString(cursor.getColumnIndexOrThrow(DATE_MOVIE)));
                arrayList.add(movieModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(MovieModel movies){
        ContentValues args = new ContentValues();
        args.put(TITLE_MOVIE, movies.getTitle_movie());
        args.put(DESC_MOVIE, movies.getDesc_movie());
        args.put(IMG_MOVIE, movies.getImg_movie());
        args.put(DATE_MOVIE, movies.getDate_movie());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteMovies(int id){
        return database.delete(TABLE_MOVIES, _ID + " = ' " + id+"'", null );
    }
}
