package com.dicoding.picodiploma.fiki.sub4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class TvShows implements Parcelable {

	@SerializedName("first_air_date")
	private final String firstAirDate;

	@SerializedName("overview")
	private final String overview;

	@SerializedName("original_language")
	private final String originalLanguage;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private final String posterPath;

	@SerializedName("origin_country")
	private final List<String> originCountry;

	@SerializedName("backdrop_path")
	private final String backdropPath;

	@SerializedName("original_name")
	private final String originalName;

	@SerializedName("popularity")
	private final double popularity;

	@SerializedName("vote_average")
	private final double voteAverage;

	@SerializedName("name")
	private final String name;

	@SerializedName("id")
	private final int id;

	@SerializedName("vote_count")
	private final int voteCount;

	private TvShows(Parcel in) {
		firstAirDate = in.readString();
		overview = in.readString();
		originalLanguage = in.readString();
		posterPath = in.readString();
		originCountry = in.createStringArrayList();
		backdropPath = in.readString();
		originalName = in.readString();
		popularity = in.readDouble();
		voteAverage = in.readDouble();
		name = in.readString();
		id = in.readInt();
		voteCount = in.readInt();
	}

	public static final Creator<TvShows> CREATOR = new Creator<TvShows>() {
		@Override
		public TvShows createFromParcel(Parcel in) {
			return new TvShows(in);
		}

		@Override
		public TvShows[] newArray(int size) {
			return new TvShows[size];
		}
	};

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public String getName(){
		return name;
	}

	public int getId() {
		return id;
	}

	@Override
 	public String toString(){
		return 
			"TvShows{" +
			"first_air_date = '" + firstAirDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",genre_ids = '" + genreIds + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",origin_country = '" + originCountry + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",original_name = '" + originalName + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstAirDate);
		dest.writeString(overview);
		dest.writeString(originalLanguage);
		dest.writeString(posterPath);
		dest.writeStringList(originCountry);
		dest.writeString(backdropPath);
		dest.writeString(originalName);
		dest.writeDouble(popularity);
		dest.writeDouble(voteAverage);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeInt(voteCount);
	}
}