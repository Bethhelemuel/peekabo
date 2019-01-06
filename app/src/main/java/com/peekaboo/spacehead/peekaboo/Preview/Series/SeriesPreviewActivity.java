package com.peekaboo.spacehead.peekaboo.Preview.Series;

import android.annotation.SuppressLint;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.KnownForModel;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People.PeoplePreviewAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeasonsAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesListVO;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesPreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class SeriesPreviewActivity extends AppCompatActivity implements SeasonsFragment.OnFragmentInteractionListener {

    String title, overview, releaseDate, poster, vote, id;
    ImageView posterImage, posterImage2;
    static TextView seriesTitle, seriesOverView;

    static RecyclerView horizontalRecyclerView;
    static RecyclerView horizontalRecyclerView2;

    private FrameLayout fragmentContainer;

    private String request_http = null;
    static SeasonsAdapter adapter;
    static PeoplePreviewAdapter peopleAdapter;
    static LinearLayoutManager linearLayoutManager;
    static LinearLayoutManager linearLayoutManager2;

    static LinearLayout seasonsView;



    private static Context mContext;
    private static Activity mActivity;

    static RelativeLayout overlay;
    static RelativeLayout gradient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_preview);

        posterImage = (ImageView) findViewById(R.id.poster);
        posterImage2 = (ImageView) findViewById(R.id.poster1);
        seriesTitle = (TextView) findViewById(R.id.seriesTitle);
        seriesOverView = (TextView) findViewById(R.id.movieOverview);
        overlay=(RelativeLayout) findViewById(R.id.overlay);
        gradient=(RelativeLayout) findViewById(R.id.gradient);

        mActivity=this;



        mContext = this;
        horizontalRecyclerView=(RecyclerView)findViewById(R.id.seasons_horizontal_recyclerView);
        horizontalRecyclerView2=(RecyclerView)findViewById(R.id.knownFor_horizontal_recyclerView);
        linearLayoutManager= new LinearLayoutManager(mContext,linearLayoutManager.HORIZONTAL,false);
        linearLayoutManager2= new LinearLayoutManager(mContext,linearLayoutManager.HORIZONTAL,false);

        seasonsView=(LinearLayout)findViewById(R.id.seasonView);


        fragmentContainer=(FrameLayout)findViewById(R.id.seasonFragment);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        overview = intent.getStringExtra("overview");
        releaseDate = intent.getStringExtra("releaseDate");
        poster = intent.getStringExtra("poster");
        vote = intent.getStringExtra("vote");
        id = intent.getStringExtra("id");


        request_http = "http://api.themoviedb.org/3/tv/" + id + "?api_key=" + Utility.api_key;


        loadFirstLayout();
        requestSeriesSpecs();

        showOverlay(false);


    }


    public void loadFirstLayout() {

        seriesOverView.setText(overview);
        seriesTitle.setText(title);

//         Picasso.with(this).load("https://image.tmdb.org/t/p/w154"+poster).fit().centerInside().into(toolbarImage);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + poster).into(posterImage);

        MultiTransformation multi = new MultiTransformation(
                new RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.ALL));

        Glide.with(this).load("https://image.tmdb.org/t/p/w185" + poster).apply(bitmapTransform(multi)).into(posterImage2);

    }


    public void requestSeriesSpecs() {

       SeriesRequestPreview requestMovieInfo = null;


        if (Utility.isOnline()) {

            requestMovieInfo = new SeriesRequestPreview();
            requestMovieInfo.execute(request_http);
        } else {

            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }


    public static void loadSecondLayout(final SeriesPreviewModel series) {

        ArrayList<SeriesListVO> seriesList = new ArrayList<>();

        ArrayList<SeriesListVO> seasons= series.getSeriesList();

        if(seasons==null){

            seasonsView.setVisibility(View.GONE);

        }else{

            seasonsView.setVisibility(View.VISIBLE);

                    if(seasons.get(0).getName().equals("Specials")){

                        seasons.remove(0);
                    }


            for(int i=0;i<seasons.size();i++){


                SeriesListVO seriesVO= new SeriesListVO();

                seriesVO.setPosterPath(seasons.get(i).getPosterPath());
                seriesVO.setOverview(seasons.get(i).getOverview());
                seriesVO.setAirDate(seasons.get(i).getAirDate());
                seriesVO.setEpisodeCount(seasons.get(i).getEpisodeCount());
                seriesVO.setName(seasons.get(i).getName());


                seriesList.add(seriesVO);
            }

            adapter= new SeasonsAdapter(mContext,seriesList);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(adapter);

        }

        }








        @SuppressLint("ResourceType")
        public static void openFragment(String title,String episodeCount,String airDate,String overview,String image){


            SeasonsFragment fragment= SeasonsFragment.newInstance(title,episodeCount,airDate,overview,image);
            FragmentManager fragmentManager= mActivity.getFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
//            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);
            transaction.addToBackStack(null);

            transaction.add(R.id.seasonFragment,fragment,"Season_Fragment").commit();

        }


    @Override
    public void onFragmentInteraction(String uri) {


        SeriesPreviewActivity.showOverlay(false);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        SeriesPreviewActivity.showOverlay(false);
        super.onBackPressed();
    }

    public static void showOverlay(boolean b){

        if(b){

            overlay.setVisibility(View.VISIBLE);
            gradient.setVisibility(View.GONE);


        }else{

        overlay.setVisibility(View.GONE);
        gradient.setVisibility(View.VISIBLE);
        }

    }
}