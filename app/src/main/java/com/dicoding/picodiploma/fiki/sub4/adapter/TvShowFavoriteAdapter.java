package com.dicoding.picodiploma.fiki.sub4.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.picodiploma.fiki.sub4.R;
import com.dicoding.picodiploma.fiki.sub4.db.DatabaseHelper;
import com.dicoding.picodiploma.fiki.sub4.db.MovieModel;
import com.dicoding.picodiploma.fiki.sub4.db.TvShowModel;
import com.dicoding.picodiploma.fiki.sub4.ui.favorites.FavoritesActivity;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TvShowFavoriteAdapter extends RecyclerView.Adapter<TvShowFavoriteAdapter.TvShowFavoriteViewHolder> {

    private final ArrayList<TvShowModel> listTvShowFavorite;
    DatabaseHelper databaseHelper;

    public TvShowFavoriteAdapter(ArrayList<TvShowModel> listTvShowFavorite) {
        this.listTvShowFavorite = listTvShowFavorite;
    }

    @NonNull
    @Override
    public TvShowFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent,false);
        databaseHelper = new DatabaseHelper(view.getContext());
        return new TvShowFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowFavoriteAdapter.TvShowFavoriteViewHolder holder, final int position) {

        final TvShowModel TvShow = listTvShowFavorite.get(position);
        String image_url = TvShow.getImg_tvshow();
        Glide.with(holder.itemView.getContext())
                .load(image_url)
                .apply(new RequestOptions().override(166, 170))
                .into(holder.imgPhoto);

        holder.tvTitle.setText(listTvShowFavorite.get(position).getTitle_tvshow());
        holder.tvDesc.setText(listTvShowFavorite.get(position).getDesc_tvshow());
        holder.tvYears.setText(listTvShowFavorite.get(position).getDate_tvshow());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d(TAG, "Number of Tv Shows ID: " + listTvShowFavorite.get(position).getId());
                alertDialogShow(listTvShowFavorite.get(position).getId(), view.getContext());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTvShowFavorite.size();
    }

    public class TvShowFavoriteViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPhoto;
        final TextView tvTitle;
        final TextView tvDesc;
        final TextView tvYears;
        public TvShowFavoriteViewHolder(@NonNull View itemView) {
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
                            db.execSQL("DELETE FROM tvshow WHERE id_tvshow ="+position+"");
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
