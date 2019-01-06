package com.peekaboo.spacehead.peekaboo.Preview.People;

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
import com.peekaboo.spacehead.peekaboo.Preview.Series.SeasonsFragment;
import com.peekaboo.spacehead.peekaboo.Preview.Series.SeriesRequestPreview;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.KnownForModel;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People.PeoplePreviewAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People.PeoplePreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeasonsAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesListVO;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesPreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class PeoplePreviewActivity extends AppCompatActivity implements PeopleFragment.OnFragmentInteractionListener {

    String name, poster, id;
    ImageView posterImage, posterImage2;

    static TextView personName, bio,placeOfBirth,bday;

    static RecyclerView horizontalRecyclerView;
    static RecyclerView horizontalRecyclerView2;

    private FrameLayout fragmentContainer;

    private String request_http = null;
    static SeasonsAdapter adapter;
    static PeoplePreviewAdapter peopleAdapter;

    static LinearLayoutManager linearLayoutManager;
    static LinearLayoutManager linearLayoutManager2;

    static LinearLayout knownForView;

    ArrayList<KnownForModel> knownForlist;

    private static Context mContext;
    private static Activity mActivity;

    static RelativeLayout overlay;
    static RelativeLayout gradient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_preview);

        posterImage = (ImageView) findViewById(R.id.poster);
        posterImage2 = (ImageView) findViewById(R.id.poster1);
        personName = (TextView) findViewById(R.id.personTitle);
        bio = (TextView)findViewById(R.id.personOverview);
        placeOfBirth = (TextView)findViewById(R.id.personPlaceOfBirth);
        bday = (TextView)findViewById(R.id.personBirthday);
        overlay=(RelativeLayout) findViewById(R.id.overlay);
        gradient=(RelativeLayout) findViewById(R.id.gradient);

        mActivity=this;



        mContext = this;
        horizontalRecyclerView=(RecyclerView)findViewById(R.id.knownFor_horizontal_recyclerView);
        horizontalRecyclerView2=(RecyclerView)findViewById(R.id.knownFor_horizontal_recyclerView);

        linearLayoutManager= new LinearLayoutManager(mContext,linearLayoutManager.HORIZONTAL,false);
        linearLayoutManager2= new LinearLayoutManager(mContext,linearLayoutManager.HORIZONTAL,false);


        knownForView=(LinearLayout)findViewById(R.id.knownForView);


        fragmentContainer=(FrameLayout)findViewById(R.id.knownForFragment);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        poster = intent.getStringExtra("profile");
        id = intent.getStringExtra("id");
        Bundle args = intent.getBundleExtra("knownFor");

        knownForlist = (ArrayList<KnownForModel>) args.getSerializable("ARRAYLIST");

        request_http=" https://api.themoviedb.org/3/person/"+id+"?api_key="+Utility.api_key+"&language=en-US";


        loadFirstLayout();
        knownForLayout();
        loadKnownFor(knownForlist);
        showOverlay(false);
    }


    public void loadFirstLayout() {

        personName.setText(name);

//         Picasso.with(this).load("https://image.tmdb.org/t/p/w154"+poster).fit().centerInside().into(toolbarImage);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + poster).into(posterImage);



        Glide.with(this).load("https://image.tmdb.org/t/p/w185" + poster).into(posterImage2);

    }


    public void knownForLayout() {

       PeopleRequestPreview requestPeopleInfo = null;


        if (Utility.isOnline()) {

            requestPeopleInfo = new PeopleRequestPreview();
            requestPeopleInfo.execute(request_http);
        } else {

            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }


    public static void loadSecondLayout(final PeoplePreviewModel person) {


            knownForView.setVisibility(View.VISIBLE);

           bio.setText(person.getBio());
           bday.setText(person.getBirthday());
           placeOfBirth.setText(person.getPlaceOfBirth());



        }

    public void loadKnownFor(ArrayList<KnownForModel> items){



        ArrayList<KnownForModel> peopleItemsList= new ArrayList<>();


        for(int i=0;i<items.size();i++){


            KnownForModel itemsList= new KnownForModel();

            itemsList.setOverview(items.get(i).getOverview());
            itemsList.setPosterPath(items.get(i).getPosterPath());
            itemsList.setReleaseDate(items.get(i).getReleaseDate());
            itemsList.setTitle(items.get(i).getTitle());


            peopleItemsList.add(itemsList);
        }

        peopleAdapter= new PeoplePreviewAdapter(mContext,peopleItemsList);
        horizontalRecyclerView2.setLayoutManager(linearLayoutManager2);
        horizontalRecyclerView2.setAdapter(peopleAdapter);

    }




        @SuppressLint("ResourceType")
        public static void openFragment(String title,String releaseDate,String overview,String image){


            PeopleFragment fragment= PeopleFragment.newInstance(title,releaseDate,overview,image);
            FragmentManager fragmentManager= mActivity.getFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
//            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);
            transaction.addToBackStack(null);

            transaction.add(R.id.knownForFragment,fragment,"People_Fragment").commit();

        }


    @Override
    public void onFragmentInteraction(String uri) {


        PeoplePreviewActivity.showOverlay(false);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        PeoplePreviewActivity.showOverlay(false);
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