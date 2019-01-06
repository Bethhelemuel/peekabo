package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series;

import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Troy on 5/10/2018.
 */

public class SeriesDetailParser {


    static ArrayList<VideoModel> series;


    public static ArrayList<VideoModel> parseJSON(String json){

        series= new ArrayList<VideoModel>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray results= (JSONArray) jsonObject.getJSONArray("results");

            for(int i=0;i<results.length();i++){

                VideoModel seriesModel= new VideoModel();

                seriesModel.setOverview(results.getJSONObject(i).getString("overview"));
                seriesModel.setPoster(results.getJSONObject(i).getString("poster_path"));
                seriesModel.setTitle(results.getJSONObject(i).getString("title"));
                seriesModel.setReleaseDate(results.getJSONObject(i).getString("release_date"));
                seriesModel.setVote(results.getJSONObject(i).getString("vote_average"));
                seriesModel.setId(results.getJSONObject(i).getString("id"));


                series.add(seriesModel);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        return series;

    }
}
