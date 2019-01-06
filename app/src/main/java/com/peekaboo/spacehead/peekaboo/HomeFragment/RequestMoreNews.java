package com.peekaboo.spacehead.peekaboo.HomeFragment;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.MoviesFragment.FragmentMovie;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Movie.MovieJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News.NewsJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News.NewsVO;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class RequestMoreNews extends AsyncTask<String,Void,ArrayList<NewsVO>>{

    public String json;
    public ArrayList<NewsVO> newsList;


    @Override
    protected void onPostExecute(ArrayList<NewsVO> newsModel) {


       FragmentHome.setMoreNewsList(newsModel);


        super.onPostExecute(newsModel);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{

            json=Utility.getJson(strings[0]);



            newsList= NewsJSONParser.parseJSON(json);


        }catch (Exception e){

            Log.v("Error  G : "," something"+e.getMessage());
        }

        return newsList;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}
