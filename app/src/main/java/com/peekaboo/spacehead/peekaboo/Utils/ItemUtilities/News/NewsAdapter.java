package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.Preview.Movies.MoviePreviewActivity;
import com.peekaboo.spacehead.peekaboo.Preview.News.NewsPreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;

import java.util.ArrayList;

/**
 * Created by Troy on 5/9/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ExampleViewHolder> implements View.OnClickListener {

    View.OnClickListener mClickListener;

    private Context mContext;
    private ArrayList<NewsVO> newsList;

    public NewsAdapter(Context context, ArrayList<NewsVO> exampleList) {
        mContext = context;
        newsList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_view, parent, false);
        ExampleViewHolder exampleViewHolder= new ExampleViewHolder(v);

        exampleViewHolder.itemView.setOnClickListener(this);

        return exampleViewHolder;
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        final NewsVO currentItem = newsList.get(position);

        final String imageUrl = currentItem.getPoster();

            Glide.with(mContext).load(imageUrl).into(holder.mImageView);
            holder.title.setText(currentItem.getTitle());
            holder.source.setText(currentItem.getSource());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("ITEMS",currentItem.getAuthor());



                Intent intent= new Intent(mContext, NewsPreviewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",currentItem.getTitle());
                intent.putExtra("author",currentItem.getAuthor());
                intent.putExtra("description",currentItem.getDescription());
                intent.putExtra("poster",imageUrl);
                intent.putExtra("url",currentItem.getUrl());
                intent.putExtra("publishDate",currentItem.getPublishedDate());
                intent.putExtra("source",currentItem.getPublishedDate());

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onClick(View v) {

            mClickListener.onClick(v);
    }




    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView source,title,poster;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.poster_imageView);
            source=itemView.findViewById(R.id.newsSource);
            title=itemView.findViewById(R.id.newsTitle);


        }
    }
}