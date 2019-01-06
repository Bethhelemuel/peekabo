package com.peekaboo.spacehead.peekaboo.HomeFragment;

import android.os.AsyncTask;
import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleParser;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleVO;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class Request extends AsyncTask<String,Void,ArrayList<Object>>{

    public String json;
    public String json2;

    public ArrayList<Object> items;
    public ArrayList<PeopleVO> peopleList;
    public ArrayList<PeopleVO> peopleList2;



    @Override
    protected void onPostExecute(ArrayList<Object> items) {

    ArrayList<PeopleVO> firstList=(ArrayList<PeopleVO>) items.get(0);
    ArrayList<PeopleVO> secondList=(ArrayList<PeopleVO>) items.get(1);



       FragmentHome.loadHorizontalPeople(firstList);
       FragmentHome.loadHorizontalPeople2(secondList);

        super.onPostExecute(items);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

        // FragmentMovie.loading(true);

        items= new ArrayList<Object>();

        try{
            json=Utility.getJson(strings[0]);
            json2=Utility.getJson(strings[1]);
            peopleList= PeopleParser.parseJSON(json);
            peopleList2= PeopleParser.parseJSON(json2);


        }catch (Exception e){

            Log.v("Error  G : "," something "+e.getMessage());
        }

        items.add(peopleList);
        items.add(peopleList2);

        return items;
    }

    @Override
    protected void onPreExecute() {


        super.onPreExecute();
    }
}