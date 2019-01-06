package com.peekaboo.spacehead.peekaboo.MoviesFragment;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Movie.MovieJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class RequestMoreMovies extends AsyncTask<String,Void,ArrayList<VideoModel>>{

    public String json;
    public ArrayList<VideoModel> movieList;


    @Override
    protected void onPostExecute(ArrayList<VideoModel> movieModels) {


       FragmentMovie.setMoreMovieList(movieModels);


        super.onPostExecute(movieModels);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{

            json=Utility.getJson(strings[0]);



            movieList= MovieJSONParser.parseJSON(json);


        }catch (Exception e){

            Log.v("Error  G : "," something"+e.getMessage());
        }

        return movieList;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}
