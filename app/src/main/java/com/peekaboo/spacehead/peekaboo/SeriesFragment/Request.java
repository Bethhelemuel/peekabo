package com.peekaboo.spacehead.peekaboo.SeriesFragment;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Series.SeriesJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class Request extends AsyncTask<String,Void,ArrayList<VideoModel>>{

    public String json;
    public ArrayList<VideoModel> seriesList;



    @Override
    protected void onPostExecute(ArrayList<VideoModel> seriesList) {

        FragmentSeries.loadHorizontalSeries(seriesList);


        super.onPostExecute(seriesList);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{
            json=Utility.getJson(strings[0]);
            seriesList= SeriesJSONParser.parseJSON(json);


        }catch (Exception e){

            Log.v("Error  G : "," something"+e.getMessage());
        }

        return seriesList;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}
