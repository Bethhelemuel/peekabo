package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.Preview.People.PeoplePreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.KnownForModel;

import java.util.ArrayList;

/**
 * Created by Troy on 5/9/2018.
 */

public class PeoplePreviewAdapter extends RecyclerView.Adapter<PeoplePreviewAdapter.ExampleViewHolder> implements View.OnClickListener {

    View.OnClickListener mClickListener;

    private Context mContext;
    private ArrayList<KnownForModel> itemList;

    public PeoplePreviewAdapter(Context context, ArrayList<KnownForModel> exampleList) {
        mContext = context;
        itemList = exampleList;
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
        final KnownForModel currentItem = itemList.get(position);

        final String imageUrl = currentItem.getPosterPath();

            holder.txtView.setText(currentItem.getTitle());
            Glide.with(mContext).load("https://image.tmdb.org/t/p/w154"+imageUrl).into(holder.mImageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=currentItem.getTitle();
                String overview=currentItem.getOverview();
                String releaseDate=currentItem.getReleaseDate();

                PeoplePreviewActivity.showOverlay(true);
                PeoplePreviewActivity.openFragment(title,releaseDate,overview,imageUrl);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
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