package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Search;


import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.KnownForModel;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleVO;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;
import com.peekaboo.spacehead.peekaboo.Utils.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Troy on 5/10/2018.
 */

public class SearchJSONParser {

    static ArrayList<Model> searchList;




    public static ArrayList<Model> parseJSON(String json){

        searchList= new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray results= (JSONArray) jsonObject.getJSONArray("results");



            for(int i=0;i<results.length();i++){

                VideoVO movieModel= new VideoVO();
                PeopleVO peopleModel= new PeopleVO();

        String media_type=results.getJSONObject(i).getString("media_type");

                if(media_type.equals("tv") || media_type.equals("movie") ){

                    movieModel.setOverview(results.getJSONObject(i).getString("overview"));
                    movieModel.setPoster(results.getJSONObject(i).getString("poster_path"));




                        if(media_type.equals("tv")){


                        movieModel.setTitle(results.getJSONObject(i).getString("name"));
                        movieModel.setReleaseDate(results.getJSONObject(i).getString("first_air_date"));



                        }else{

                        movieModel.setTitle(results.getJSONObject(i).getString("title"));
                        movieModel.setReleaseDate(results.getJSONObject(i).getString("release_date"));


                        }




                    movieModel.setId(results.getJSONObject(i).getString("id"));

                    Log.v("BIG BOY ",""+movieModel);

                    searchList.add(movieModel);


                }else{

                    peopleModel.setName(results.getJSONObject(i).getString("name"));
                    peopleModel.setProfilePath(results.getJSONObject(i).getString("profile_path"));
                    peopleModel.setId(results.getJSONObject(i).getString("id"));

                    JSONArray knownFor=(JSONArray) results.getJSONObject(i).getJSONArray("known_for");
                    ArrayList<KnownForModel> knownForModels= new ArrayList<KnownForModel>();

                    for(int k=0;k<knownFor.length();k++){

                        KnownForModel newModel= new KnownForModel();

                        newModel.setTitle(knownFor.getJSONObject(k).getString("title"));
                        newModel.setPosterPath(knownFor.getJSONObject(k).getString("poster_path"));
                        newModel.setOverview(knownFor.getJSONObject(k).getString("overview"));
                        newModel.setReleaseDate(knownFor.getJSONObject(k).getString("release_date"));


                        knownForModels.add(newModel);
//
                    }

                    peopleModel.setKnownForModel(knownForModels);


                        searchList.add(peopleModel);


                }


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return searchList;

    }
}
