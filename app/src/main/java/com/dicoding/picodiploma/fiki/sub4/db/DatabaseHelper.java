package com.dicoding.picodiploma.fiki.sub4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbcatalogmovie";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+"" +
                    "%s INTEGER NOT NULL, "+"" +
                    "%s TEXT NOT NULL, "+"" +
                    "%s TEXT NOT NULL, "+"" +
                    "%s TEXT NOT NULL,"+"" +
                    "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_MOVIES,
            DatabaseContract.MovieColumn._ID,
            DatabaseContract.MovieColumn.ID_MOVIE,
            DatabaseContract.MovieColumn.TITLE_MOVIE,
            DatabaseContract.MovieColumn.IMG_MOVIE,
            DatabaseContract.MovieColumn.DESC_MOVIE,
            DatabaseContract.MovieColumn.DATE_MOVIE
            );

    private static final String SQL_CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+"" +
                    "%s INTEGER NOT NULL, "+"" +
                    "%s TEXT NOT NULL, "+"" +
                    "%s TEXT NOT NULL, "+"" +
                    "%s TEXT NOT NULL,"+"" +
                    "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_TVSHOW,
            DatabaseContract.TvshowColumn._ID,
            DatabaseContract.TvshowColumn.ID_TVSHOW,
            DatabaseContract.TvshowColumn.TITLE_TVSHOW,
            DatabaseContract.TvshowColumn.IMG_TVSHOW,
            DatabaseContract.TvshowColumn.DESC_TVSHOW,
            DatabaseContract.TvshowColumn.DATE_TVSHOW
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_MOVIES);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_TVSHOW);
        onCreate(sqLiteDatabase);
    }
}
