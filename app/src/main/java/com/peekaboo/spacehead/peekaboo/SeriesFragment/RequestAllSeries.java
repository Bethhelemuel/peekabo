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

public class RequestAllSeries extends AsyncTask<String, Void, ArrayList<ArrayList<VideoModel>>> {

    public String json;
    public String json2;
    public ArrayList<VideoModel> seriesList;
    public ArrayList<VideoModel> moreSeriesList;


    ArrayList<ArrayList<VideoModel>> items ;

    @Override
    protected void onPostExecute(ArrayList<ArrayList<VideoModel>> objects) {

        FragmentSeries.loadAllSeries(objects.get(0));
        FragmentSeries.setMoreSeriesList(objects.get(1));
        super.onPostExecute(objects);
    }

    @Override
    protected ArrayList<ArrayList<VideoModel>> doInBackground(String... strings) {

       // FragmentMovie.loading(true);
        items =new ArrayList<ArrayList<VideoModel>>();

        try{


            json=Utility.getJson(strings[0]);
            json2=Utility.getJson(strings[1]);



            seriesList= SeriesJSONParser.parseJSON(json);
            moreSeriesList= SeriesJSONParser.parseJSON(json2);






            items.add(seriesList);
            items.add(moreSeriesList);

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
