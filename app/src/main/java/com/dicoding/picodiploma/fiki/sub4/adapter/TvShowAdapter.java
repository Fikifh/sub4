package com.dicoding.picodiploma.fiki.sub4.adapter;

import android.app.Activity;
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
import com.dicoding.picodiploma.fiki.sub4.activity.TvShowDetailActivity;
import com.dicoding.picodiploma.fiki.sub4.model.TvShows;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private final ArrayList<TvShows> listTShows;
    private Context context;
    private Activity activity;
    private static final String IMAGE_URL_BASE_PATH= "https://image.tmdb.org/t/p/w185/";

    public TvShowAdapter(ArrayList<TvShows> listTShows) {
        this.listTShows = listTShows;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);
        return new TvShowAdapter.TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, int i) {

        String image_url = IMAGE_URL_BASE_PATH + listTShows.get(i).getPosterPath();
        final TvShows tvShows = listTShows.get(i);
        Glide.with(tvShowViewHolder.itemView.getContext())
                .load(image_url)
                .apply(new RequestOptions().override(166, 170))
                .into(tvShowViewHolder.imgPhoto);

        tvShowViewHolder.tvTitle.setText(listTShows.get(i).getName());
        tvShowViewHolder.tvDesc.setText(listTShows.get(i).getOverview());
        tvShowViewHolder.tvYears.setText(listTShows.get(i).getFirstAirDate());
        tvShowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), TvShowDetailActivity.class);
                intent.putExtra("EXTRAS", tvShows);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTShows.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPhoto;
        final TextView tvTitle;
        final TextView tvDesc;
        final TextView tvYears;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo_id);
            tvTitle = itemView.findViewById(R.id.tv_title_id);
            tvDesc = itemView.findViewById(R.id.tv_desc_id);
            tvYears = itemView.findViewById(R.id.tv_year_id);
        }
    }
}
