package com.dicoding.picodiploma.fiki.sub4.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;


public class MoviesResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<Movies> results;

	@SerializedName("total_results")
	private int totalResults;

	public ArrayList<Movies> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"MoviesResponse{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}