package com.dicoding.picodiploma.fiki.sub4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Movies implements Parcelable {

	@SerializedName("overview")
	private final String overview;

	@SerializedName("original_language")
	private final String originalLanguage;

	@SerializedName("original_title")
	private final String originalTitle;

	@SerializedName("video")
	private final boolean video;

	@SerializedName("title")
	private final String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private final String posterPath;

	@SerializedName("backdrop_path")
	private final String backdropPath;

	@SerializedName("release_date")
	private final String releaseDate;

	@SerializedName("vote_average")
	private final double voteAverage;

	@SerializedName("popularity")
	private final double popularity;

	@SerializedName("id")
	private final int id;

	@SerializedName("adult")
	private final boolean adult;

	@SerializedName("vote_count")
	private final int voteCount;

	public Movies(Parcel in) {
		overview = in.readString();
		originalLanguage = in.readString();
		originalTitle = in.readString();
		video = in.readByte() != 0;
		title = in.readString();
		posterPath = in.readString();
		backdropPath = in.readString();
		releaseDate = in.readString();
		voteAverage = in.readDouble();
		popularity = in.readDouble();
		id = in.readInt();
		adult = in.readByte() != 0;
		voteCount = in.readInt();
	}

	public static final Creator<Movies> CREATOR = new Creator<Movies>() {
		@Override
		public Movies createFromParcel(Parcel in) {
			return new Movies(in);
		}

		@Override
		public Movies[] newArray(int size) {
			return new Movies[size];
		}
	};

	public int getId() {
		return id;
	}

	public String getOverview(){
		return overview;
	}

    public String getTitle(){
		return title;
	}

    public String getPosterPath(){
		return posterPath;
	}

    public String getReleaseDate(){
		return releaseDate;
	}

    @Override
 	public String toString(){
		return 
			"Movies{" +
			"overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",original_title = '" + originalTitle + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",genre_ids = '" + genreIds + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(overview);
		dest.writeString(originalLanguage);
		dest.writeString(originalTitle);
		dest.writeByte((byte) (video ? 1 : 0));
		dest.writeString(title);
		dest.writeString(posterPath);
		dest.writeString(backdropPath);
		dest.writeString(releaseDate);
		dest.writeDouble(voteAverage);
		dest.writeDouble(popularity);
		dest.writeInt(id);
		dest.writeByte((byte) (adult ? 1 : 0));
		dest.writeInt(voteCount);
	}


}