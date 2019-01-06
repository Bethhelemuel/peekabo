package com.peekaboo.spacehead.peekaboo.HomeFragment;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.MoviesFragment.FragmentMovie;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News.NewsJSONParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News.NewsVO;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleVO;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class NewsRequest extends AsyncTask<String,Void,ArrayList<ArrayList<NewsVO>>> {

    public String json;
    public String json2;

    public ArrayList<NewsVO> newsList;
    public ArrayList<NewsVO> newsList2;


    ArrayList<ArrayList<NewsVO>> items ;


    @Override
    protected void onPostExecute(ArrayList<ArrayList<NewsVO>> objects) {


        FragmentHome.setMoreNewsList(objects.get(1));
       FragmentHome.loadNews(objects.get(0));



        super.onPostExecute(objects);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

        items =new ArrayList<ArrayList<NewsVO>>();


        try{
            json=Utility.getJson(strings[0]);
            json2=Utility.getJson(strings[1]);



            newsList= NewsJSONParser.parseJSON(json);
            newsList2= NewsJSONParser.parseJSON(json2);

            items.add(newsList);
            items.add(newsList2);

        }catch (Exception e){

            Log.v("Error  G : "," something "+e.getMessage());
        }


        return items;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}