package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Series;

import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesListVO;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesPreviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Troy on 5/10/2018.
 */

public class SeasonsJSONParser {


    static SeriesPreviewModel seriesPreviewModel;


    public static SeriesPreviewModel parseJSON(String json){

        seriesPreviewModel= new SeriesPreviewModel();
        ArrayList<SeriesListVO> seriesList= new ArrayList<SeriesListVO>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray results= (JSONArray) jsonObject.getJSONArray("seasons");

            for(int i=0;i<results.length();i++){

                SeriesListVO seriesModel= new SeriesListVO();

                seriesModel.setAirDate(results.getJSONObject(i).getString("air_date"));
                seriesModel.setEpisodeCount(results.getJSONObject(i).getString("episode_count"));
                seriesModel.setId(results.getJSONObject(i).getString("id"));
                seriesModel.setName(results.getJSONObject(i).getString("name"));
                seriesModel.setOverview(results.getJSONObject(i).getString("overview"));
                seriesModel.setPosterPath(results.getJSONObject(i).getString("poster_path"));
                seriesModel.setSeasonNumber(results.getJSONObject(i).getString("season_number"));

                seriesList.add(seriesModel);
            }


            seriesPreviewModel.setSeriesList(seriesList);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return seriesPreviewModel;

    }
}
