package com.peekaboo.spacehead.peekaboo.SearchFragment;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Search.SearchJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.Model;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class Request extends AsyncTask<String,Void,ArrayList<Model>>{

    public String json;
    public ArrayList<Model> searchList;



    @Override
    protected void onPostExecute(ArrayList<Model> searchList) {

        SearchLayoutFragment.loadItems(searchList);


        super.onPostExecute(searchList);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

       // FragmentMovie.loading(true);

        try{
            json=Utility.getJson(strings[0]);




            searchList= SearchJSONParser.parseJSON(json);


        }catch (Exception e){

            Log.v("Error  G : "," something"+e.getMessage());
        }

        return searchList;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}
