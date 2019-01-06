package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Troy on 5/10/2018.
 */

public class MoviePreviewParser {


    static MoviePreviewModel movies;


    public static MoviePreviewModel parseJSON(String json){

        movies= new MoviePreviewModel();

        try {
            JSONObject jsonObject = new JSONObject(json);



            movies.setBudget(jsonObject.getString("budget"));
            movies.setHomepage(jsonObject.getString("homepage"));
            movies.setRevenue(jsonObject.getString("revenue"));
            movies.setTagline(jsonObject.getString("tagline"));
            movies.setStatus(jsonObject.getString("status"));






        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;

    }
}
