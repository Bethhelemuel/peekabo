package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Series;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.Preview.Series.SeriesPreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;

import java.util.ArrayList;

/**
 * Created by Troy on 5/9/2018.
 */

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ExampleViewHolder> implements View.OnClickListener {

    View.OnClickListener mClickListener;

    private Context mContext;
    private ArrayList<VideoVO> movieList;

    public SeriesAdapter(Context context, ArrayList<VideoVO> exampleList) {
        mContext = context;
        movieList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.movie_scroll_item, parent, false);
        ExampleViewHolder exampleViewHolder= new ExampleViewHolder(v);

        exampleViewHolder.itemView.setOnClickListener(this);

        return exampleViewHolder;
    }
    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        final VideoVO currentItem = movieList.get(position);

        final String imageUrl = currentItem.getPoster();

            Glide.with(mContext).load("https://image.tmdb.org/t/p/w154"+imageUrl).into(holder.mImageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(mContext, SeriesPreviewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",currentItem.getTitle());
                intent.putExtra("overview",currentItem.getOverview());
                intent.putExtra("releaseDate",currentItem.getReleaseDate());
                intent.putExtra("poster",imageUrl);
                intent.putExtra("vote",currentItem.getVote());
                intent.putExtra("id",currentItem.getId());


                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onClick(View v) {

            mClickListener.onClick(v);
    }




    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.poster);

        }
    }
}