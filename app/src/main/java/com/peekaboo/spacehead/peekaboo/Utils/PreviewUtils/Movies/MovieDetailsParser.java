package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies;

import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Troy on 5/10/2018.
 */

public class MovieDetailsParser {


    static ArrayList<VideoModel> movies;


    public static ArrayList<VideoModel> parseJSON(String json){

        movies= new ArrayList<VideoModel>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray results= (JSONArray) jsonObject.getJSONArray("results");

            for(int i=0;i<results.length();i++){

                VideoModel movieModel= new VideoModel();

                movieModel.setOverview(results.getJSONObject(i).getString("overview"));
                movieModel.setPoster(results.getJSONObject(i).getString("poster_path"));
                movieModel.setTitle(results.getJSONObject(i).getString("title"));
                movieModel.setReleaseDate(results.getJSONObject(i).getString("release_date"));
                movieModel.setVote(results.getJSONObject(i).getString("vote_average"));
                movieModel.setId(results.getJSONObject(i).getString("id"));

                movies.add(movieModel);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;

    }
}
