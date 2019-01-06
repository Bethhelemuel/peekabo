package com.peekaboo.spacehead.peekaboo.Preview.Movies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies.MoviePreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class MoviePreviewActivity extends AppCompatActivity {

    String title,overview,releaseDate,poster,vote,id;
    ImageView posterImage,posterImage2;
    static TextView movieTitle,movieVote,movieOverview,tagLine,budget,revenue,homepage,status;
    CollapsingToolbarLayout collapsingToolbarLayout;

    private  String request_http=null;
    private  String request_http_content=null;

    private static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_preview);

        posterImage=(ImageView)findViewById(R.id.poster);
        posterImage2=(ImageView)findViewById(R.id.poster1);
        movieTitle=(TextView)findViewById(R.id.movieTitle);
        movieVote=(TextView)findViewById(R.id.movieVote);
        movieOverview=(TextView)findViewById(R.id.movieOverview);
        tagLine=(TextView)findViewById(R.id.movieTagLine);
        budget=(TextView)findViewById(R.id.movieBudget);
        revenue=(TextView)findViewById(R.id.movieRevenue);
        homepage=(TextView)findViewById(R.id.movieHomepage);
        status=(TextView)findViewById(R.id.movieStatus);


        mContext=this;

        Intent intent = getIntent();
        title=intent.getStringExtra("title");
        overview=intent.getStringExtra("overview");
        releaseDate=intent.getStringExtra("releaseDate");
        poster=intent.getStringExtra("poster");
        vote=intent.getStringExtra("vote");
        id=intent.getStringExtra("id");

        request_http="http://api.themoviedb.org/3/movie/"+id+"?api_key="+Utility.api_key;
        request_http_content="https://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=4945275050f30e961d264fe1ff6cce1f";
        loadFirstLayout();
        requestMovieSpecs();
    }


    public void loadFirstLayout(){
        movieTitle.setText(title);
        movieVote.setText(vote);
        movieOverview.setText(overview);


//         Picasso.with(this).load("https://image.tmdb.org/t/p/w154"+poster).fit().centerInside().into(toolbarImage);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+poster).into(posterImage);

        MultiTransformation multi = new MultiTransformation(
                new RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.ALL));

        Glide.with(this).load("https://image.tmdb.org/t/p/w185"+poster).apply(bitmapTransform(multi)).into(posterImage2);

    }


    public void requestMovieSpecs(){

        MovieRequestPreview requestMovieInfo=null;


        if(Utility.isOnline()){

            requestMovieInfo= new MovieRequestPreview();
            requestMovieInfo.execute(request_http);
        }else{

            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }


    public static void loadSecondLayout(final MoviePreviewModel movie){


       tagLine.setText(movie.getTagline());
       budget.setText(movie.getBudget());
       revenue.setText(movie.getRevenue());
       status.setText(movie.getStatus());


       if(movie.getHomepage().equals("null")){

           homepage.setText("Can't find site");
           homepage.setTextColor(Color.parseColor("#000000"));
       }else{
           homepage.setText("Click here");
           homepage.setTextColor(Color.parseColor("#1036F1"));
           homepage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getHomepage()));
                   mContext.startActivity(browserIntent);
               }
           });

       }



    }


}
