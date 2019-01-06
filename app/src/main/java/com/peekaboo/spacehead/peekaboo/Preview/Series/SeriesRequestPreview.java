package com.peekaboo.spacehead.peekaboo.Preview.Series;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesPreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Series.SeasonsJSONParser;

/**
 * Created by Troy on 5/8/2018.
 */

public class SeriesRequestPreview extends AsyncTask<String,Void,SeriesPreviewModel>{

    public String json;
    public SeriesPreviewModel seriesList;



    @Override
    protected void onPostExecute(SeriesPreviewModel series) {



        SeriesPreviewActivity.loadSecondLayout(series);



        super.onPostExecute(series);
    }

    @Override
    protected SeriesPreviewModel doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{
            json=Utility.getJson(strings[0]);
            seriesList= SeasonsJSONParser.parseJSON(json);

            Log.v("REQUEST",json);


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
