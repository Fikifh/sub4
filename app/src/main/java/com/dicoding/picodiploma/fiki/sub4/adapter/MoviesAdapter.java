package com.dicoding.picodiploma.fiki.sub4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.activity.MoviesDetailActivity;
import com.dicoding.picodiploma.fiki.sub4.model.Movies;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private final ArrayList<Movies> listMovies;
    private Context context;
    private static final String IMAGE_URL_BASE_PATH= "https://image.tmdb.org/t/p/w185/";

    public MoviesAdapter(ArrayList<Movies> listMovies) {
        this.listMovies = listMovies;
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder viewHolder, int i) {
        String image_url = IMAGE_URL_BASE_PATH + listMovies.get(i).getPosterPath();
        final Movies movies = listMovies.get(i);
        Glide.with(viewHolder.itemView.getContext())
                .load(image_url)
                .apply(new RequestOptions().override(166, 170))
                .into(viewHolder.imgPhoto);

        viewHolder.tvTitle.setText(listMovies.get(i).getTitle());
        viewHolder.tvDesc.setText(listMovies.get(i).getOverview());
        viewHolder.tvYears.setText(listMovies.get(i).getReleaseDate());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MoviesDetailActivity.class);
                intent.putExtra("EXTRAS", movies);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    @SuppressWarnings("SameReturnValue")
    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPhoto;
        final TextView tvTitle;
        final TextView tvDesc;
        final TextView tvYears;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo_id);
            tvTitle = itemView.findViewById(R.id.tv_title_id);
            tvDesc = itemView.findViewById(R.id.tv_desc_id);
            tvYears = itemView.findViewById(R.id.tv_year_id);
        }

    }
}
