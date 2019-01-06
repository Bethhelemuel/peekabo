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

public class RequestAllMovies extends AsyncTask<String, Void, ArrayList<ArrayList<VideoModel>>> {

    public String json;
    public String json2;
    public ArrayList<VideoModel> movieList;
    public ArrayList<VideoModel> moreMovieList;


    ArrayList<ArrayList<VideoModel>> items ;

    @Override
    protected void onPostExecute(ArrayList<ArrayList<VideoModel>> objects) {

        FragmentMovie.loadAllMovies(objects.get(0));
        FragmentMovie.setMoreMovieList(objects.get(1));
        super.onPostExecute(objects);
    }

    @Override
    protected ArrayList<ArrayList<VideoModel>> doInBackground(String... strings) {

       // FragmentMovie.loading(true);
        items =new ArrayList<ArrayList<VideoModel>>();

        try{


            json=Utility.getJson(strings[0]);
            json2=Utility.getJson(strings[1]);



            movieList= MovieJSONParser.parseJSON(json);
            moreMovieList= MovieJSONParser.parseJSON(json2);






            items.add(movieList);
            items.add(moreMovieList);

            Log.v("ITEMS",""+items.size());

        }catch (Exception e){

            Log.v("Error  G : "," something"+e.getMessage());
        }


        return items;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}
