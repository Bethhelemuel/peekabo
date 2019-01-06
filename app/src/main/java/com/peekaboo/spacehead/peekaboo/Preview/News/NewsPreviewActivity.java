package com.peekaboo.spacehead.peekaboo.Preview.News;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.peekaboo.spacehead.peekaboo.Preview.Movies.MovieRequestPreview;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies.MoviePreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class NewsPreviewActivity extends AppCompatActivity {

    String title,author,description,url,poster,publishDate,source;
    ImageView newsPoster;
    static TextView newsTitle,newsAuthor,newsDescription,newsPublishDate,newsSource;
    Button goToSite;

    private static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_preview);

        newsPoster=(ImageView)findViewById(R.id.newsPoster);
        newsTitle=(TextView)findViewById(R.id.newsTitle);
        newsAuthor=(TextView)findViewById(R.id.newsAuthor);
        newsDescription=(TextView)findViewById(R.id.newsDescription);
        newsPublishDate=(TextView)findViewById(R.id.newsPublishDate);
        newsSource=(TextView)findViewById(R.id.newsSource);

        goToSite=(Button)findViewById(R.id.newsSite) ;

        mContext=this;

        Intent intent = getIntent();
        title=intent.getStringExtra("title");
        author=intent.getStringExtra("author");
        description=intent.getStringExtra("description");
        url=intent.getStringExtra("url");
        publishDate=intent.getStringExtra("publishDate");
        poster=intent.getStringExtra("poster");




        loadLayout();

    }


    public void loadLayout(){
        newsTitle.setText(title);
        newsAuthor.setText(author);
        newsPublishDate.setText(publishDate);
        newsDescription.setText(description);


//         Picasso.with(this).load("https://image.tmdb.org/t/p/w154"+poster).fit().centerInside().into(toolbarImage);
        Glide.with(this).load(poster).into(newsPoster);



    goToSite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(browserIntent);
        }
    });
    }




}
