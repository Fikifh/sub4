package com.dicoding.picodiploma.fiki.sub4.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.activity.MoviesDetailActivity;
import com.dicoding.picodiploma.fiki.sub4.db.DatabaseHelper;
import com.dicoding.picodiploma.fiki.sub4.db.MovieModel;
import com.dicoding.picodiploma.fiki.sub4.ui.favorites.FavoritesActivity;
import com.dicoding.picodiploma.fiki.sub4.ui.favorites.MoviesFavoriteFragment;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MoviesFavoriteAdapter extends RecyclerView.Adapter<MoviesFavoriteAdapter.MoviesFavoriteViewHolder> {

    private final ArrayList<MovieModel> listMoviesFavorite;
    private Context context;
    private static final String IMAGE_URL_BASE_PATH= "https://image.tmdb.org/t/p/w185/";
    DatabaseHelper databaseHelper;

    public MoviesFavoriteAdapter(ArrayList<MovieModel> listMoviesFavorite) {
        this.listMoviesFavorite = listMoviesFavorite;
    }

    @NonNull
    @Override
    public MoviesFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent,false);
        databaseHelper = new DatabaseHelper(view.getContext());
        return new MoviesFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesFavoriteAdapter.MoviesFavoriteViewHolder holder, final int position) {

        final MovieModel movies = listMoviesFavorite.get(position);
        String image_url = movies.getImg_movie();
                Glide.with(holder.itemView.getContext())
                .load(image_url)
                .apply(new RequestOptions().override(166, 170))
                .into(holder.imgPhoto);

        holder.tvTitle.setText(listMoviesFavorite.get(position).getTitle_movie());
        holder.tvDesc.setText(listMoviesFavorite.get(position).getDesc_movie());
        holder.tvYears.setText(listMoviesFavorite.get(position).getDate_movie());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d(TAG, "Number of Tv Shows ID: " + listMoviesFavorite.get(position).getId());
                alertDialogShow(listMoviesFavorite.get(position).getId(), view.getContext());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMoviesFavorite.size();
    }

    public class MoviesFavoriteViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPhoto;
        final TextView tvTitle;
        final TextView tvDesc;
        final TextView tvYears;
        public MoviesFavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo_id);
            tvTitle = itemView.findViewById(R.id.tv_title_id);
            tvDesc = itemView.findViewById(R.id.tv_desc_id);
            tvYears = itemView.findViewById(R.id.tv_year_id);
        }
    }

    private void alertDialogShow(final int position, final Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(R.string.titleDelete);

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(R.string.yConfirm)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();
                        try {
                            db.execSQL("DELETE FROM movie WHERE id_movie ="+position+"");
                            Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                            reIntent(context);
                        } catch (Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    private void reIntent(Context context){
        Intent intent = new Intent(context, FavoritesActivity.class);
        context.startActivity(intent);
    }
}
