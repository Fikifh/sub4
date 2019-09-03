package com.dicoding.picodiploma.fiki.sub4.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIES = "movie";
    static String TABLE_TVSHOW = "tvshow";

    static final class MovieColumn implements BaseColumns{
        static String  ID_MOVIE = "id_movie";
        static String TITLE_MOVIE = "title_movie";
        static String IMG_MOVIE = "img_movie";
        static String DESC_MOVIE = "desc_movie";
        static String DATE_MOVIE = "date_movie";
    }

    static final class TvshowColumn implements BaseColumns{
        static String  ID_TVSHOW = "id_tvshow";
        static String TITLE_TVSHOW = "title_tvshow";
        static String IMG_TVSHOW = "img_tvshow";
        static String DESC_TVSHOW = "desc_tvshow";
        static String DATE_TVSHOW = "date_tvshow";
    }
}
