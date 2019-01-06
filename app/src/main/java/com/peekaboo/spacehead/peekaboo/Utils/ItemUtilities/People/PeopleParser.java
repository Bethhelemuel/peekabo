package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PeopleParser {

    static ArrayList<PeopleVO> people;
    static ArrayList<KnownForModel> knownForModels;


    public static ArrayList<PeopleVO> parseJSON(String json){




        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray results= (JSONArray) jsonObject.getJSONArray("results");



            people= new ArrayList<PeopleVO>();

            for(int i=0;i<results.length() ;i++){

                PeopleVO peopleList= new PeopleVO();

                peopleList.setName(results.getJSONObject(i).getString("name"));
                peopleList.setProfilePath(results.getJSONObject(i).getString("profile_path"));
                peopleList.setId(results.getJSONObject(i).getString("id"));

                JSONArray knownFor=(JSONArray) results.getJSONObject(i).getJSONArray("known_for");
                knownForModels= new ArrayList<KnownForModel>();

                                 for(int k=0;k<knownFor.length();k++){

                                        KnownForModel newModel= new KnownForModel();

                                      newModel.setTitle(knownFor.getJSONObject(k).getString("title"));
                                      newModel.setPosterPath(knownFor.getJSONObject(k).getString("poster_path"));
                                      newModel.setOverview(knownFor.getJSONObject(k).getString("overview"));
                                      newModel.setReleaseDate(knownFor.getJSONObject(k).getString("release_date"));


                                           knownForModels.add(newModel);
//
                                   }

                peopleList.setKnownForModel(knownForModels);
                people.add(peopleList);



            }






        } catch (JSONException e) {
            e.printStackTrace();
        }



        return people;

    }
}
