package com.peekaboo.spacehead.peekaboo.Preview.Movies;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies.MoviePreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies.MoviePreviewParser;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

/**
 * Created by Troy on 5/8/2018.
 */

public class MovieRequestPreview extends AsyncTask<String,Void,MoviePreviewModel>{

    public String json;
    public MoviePreviewModel movieList;



    @Override
    protected void onPostExecute(MoviePreviewModel movie) {



        MoviePreviewActivity.loadSecondLayout(movie);


        super.onPostExecute(movie);
    }

    @Override
    protected MoviePreviewModel doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{
            json=Utility.getJson(strings[0]);
            movieList= MoviePreviewParser.parseJSON(json);

            Log.v("REQUEST",json);


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
