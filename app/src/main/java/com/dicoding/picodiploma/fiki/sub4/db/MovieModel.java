package com.dicoding.picodiploma.fiki.sub4.db;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel  implements Parcelable {
    private int id;
    private String title_movie, img_movie, desc_movie, date_movie;

    public MovieModel() {
    }

    public MovieModel(int id, String title_movie, String img_movie, String desc_movie, String date_movie) {
        this.id = id;
        this.title_movie = title_movie;
        this.img_movie = img_movie;
        this.desc_movie = desc_movie;
        this.date_movie = date_movie;
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        title_movie = in.readString();
        img_movie = in.readString();
        desc_movie = in.readString();
        date_movie = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_movie() {
        return title_movie;
    }

    public void setTitle_movie(String title_movie) {
        this.title_movie = title_movie;
    }

    public String getImg_movie() {
        return img_movie;
    }

    public void setImg_movie(String img_movie) {
        this.img_movie = img_movie;
    }

    public String getDesc_movie() {
        return desc_movie;
    }

    public void setDesc_movie(String desc_movie) {
        this.desc_movie = desc_movie;
    }

    public String getDate_movie() {
        return date_movie;
    }

    public void setDate_movie(String date_movie) {
        this.date_movie = date_movie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title_movie);
        parcel.writeString(img_movie);
        parcel.writeString(desc_movie);
        parcel.writeString(date_movie);
    }
}
