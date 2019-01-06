package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.Preview.People.PeoplePreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleVO;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;
import com.peekaboo.spacehead.peekaboo.Utils.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Troy on 5/9/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

ArrayList<Model> dataSet;
Context mContext;



public SearchAdapter(ArrayList<Model> data, Context context){

    this.dataSet=data;
    this.mContext=context;
}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;

            switch(viewType){

                case 0:
                    view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_people_item,parent,false);
                    return new PeopleHolder(view);



                case 1:

                    view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_video_item,parent,false);
                    return new VideoHolder(view);
            }

            return null;
        }









    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int listPosition) {

        Model object = dataSet.get(listPosition);
        if (object != null) {

            switch (object.type) {
                case 0:
                    PeopleVO people= (PeopleVO) dataSet.get(listPosition);

                    Glide.with(mContext).load("https://image.tmdb.org/t/p/w154"+people.getProfilePath()).into(((PeopleHolder)holder).mImageView);

                    break;

                case 1:

                    VideoVO video= (VideoVO) dataSet.get(listPosition);
                    Glide.with(mContext).load("https://image.tmdb.org/t/p/w154"+video.getPoster()).into(((VideoHolder)holder).mImageView);

                    break;

            }
        }



    }

    @Override
    public int getItemCount() {

    return dataSet.size();
    }




    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return 0;
            case 1:
                return 1;

            default:
                return -1;
        }


    }






    class PeopleHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;

        public PeopleHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.search_people);
        }
    }

    class VideoHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;

        public VideoHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.searchVideo);
        }
    }

}