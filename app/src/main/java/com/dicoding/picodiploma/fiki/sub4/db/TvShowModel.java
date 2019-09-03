package com.dicoding.picodiploma.fiki.sub4.db;


import android.os.Parcel;
import android.os.Parcelable;

public class TvShowModel implements Parcelable {
    private int id;
    private String title_tvshow, img_tvshow, desc_tvshow, date_tvshow;

    public TvShowModel() {
    }

    public TvShowModel(int id, String title_tvshow, String img_tvshow, String desc_tvshow, String date_tvshow) {
        this.id = id;
        this.title_tvshow = title_tvshow;
        this.img_tvshow = img_tvshow;
        this.desc_tvshow = desc_tvshow;
        this.date_tvshow = date_tvshow;
    }

    protected TvShowModel(Parcel in) {
        id = in.readInt();
        title_tvshow = in.readString();
        img_tvshow = in.readString();
        desc_tvshow = in.readString();
        date_tvshow = in.readString();
    }

    public static final Creator<TvShowModel> CREATOR = new Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel in) {
            return new TvShowModel(in);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_tvshow() {
        return title_tvshow;
    }

    public void setTitle_tvshow(String title_tvshow) {
        this.title_tvshow = title_tvshow;
    }

    public String getImg_tvshow() {
        return img_tvshow;
    }

    public void setImg_tvshow(String img_tvshow) {
        this.img_tvshow = img_tvshow;
    }

    public String getDesc_tvshow() {
        return desc_tvshow;
    }

    public void setDesc_tvshow(String desc_tvshow) {
        this.desc_tvshow = desc_tvshow;
    }

    public String getDate_tvshow() {
        return date_tvshow;
    }

    public void setDate_tvshow(String date_tvshow) {
        this.date_tvshow = date_tvshow;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title_tvshow);
        parcel.writeString(img_tvshow);
        parcel.writeString(desc_tvshow);
        parcel.writeString(date_tvshow);
    }
}
