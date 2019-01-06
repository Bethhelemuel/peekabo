package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.Preview.Series.SeriesPreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;

import java.util.ArrayList;

/**
 * Created by Troy on 5/9/2018.
 */

public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.ExampleViewHolder> implements View.OnClickListener {

    View.OnClickListener mClickListener;

    private Context mContext;
    private ArrayList<SeriesListVO> seriesList;

    public SeasonsAdapter(Context context, ArrayList<SeriesListVO> exampleList) {
        mContext = context;
        seriesList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.seasons_scroll_item, parent, false);
        ExampleViewHolder exampleViewHolder= new ExampleViewHolder(v);

        exampleViewHolder.itemView.setOnClickListener(this);

        return exampleViewHolder;
    }
    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        final SeriesListVO currentItem = seriesList.get(position);

        final String imageUrl = currentItem.getPosterPath();

            holder.txtView.setText(currentItem.getName());
            Glide.with(mContext).load("https://image.tmdb.org/t/p/w154"+imageUrl).into(holder.mImageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=currentItem.getName();
                String overview=currentItem.getOverview();
                String airDate=currentItem.getAirDate();
                String episodeCount=currentItem.getEpisodeCount();

                SeriesPreviewActivity.showOverlay(true);

                SeriesPreviewActivity.openFragment(title,episodeCount,airDate,overview,imageUrl);

            }
        });

    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    @Override
    public void onClick(View v) {

            mClickListener.onClick(v);
    }




    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView txtView;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.seasons_poster);
            txtView=itemView.findViewById(R.id.seasonNumber);

        }
    }
}