package com.peekaboo.spacehead.peekaboo.Preview.People;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Preview.Series.SeriesPreviewActivity;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Series.SeasonsJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People.PeoplePreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People.PeoplePreviewParser;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesPreviewModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

/**
 * Created by Troy on 5/8/2018.
 */

public class PeopleRequestPreview extends AsyncTask<String,Void,PeoplePreviewModel>{

    public String json;
    public PeoplePreviewModel peopleModel;



    @Override
    protected void onPostExecute(PeoplePreviewModel peopleModel) {



        PeoplePreviewActivity.loadSecondLayout(peopleModel);



        super.onPostExecute(peopleModel);
    }

    @Override
    protected PeoplePreviewModel doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{
            json=Utility.getJson(strings[0]);
            peopleModel= PeoplePreviewParser.parseJSON(json);

            Log.v("REQUEST",json);


        }catch (Exception e){

            Log.v("Error  G : "," something"+e.getMessage());
        }

        return peopleModel;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}
