package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Troy on 5/10/2018.
 */

public class SeriesPreviewParser {


    static SeriesPreviewModel series;


    public static SeriesPreviewModel parseJSON(String json){

        series= new SeriesPreviewModel();

        try {
            JSONObject jsonObject = new JSONObject(json);



            series.setHomepage(jsonObject.getString("homepage"));
            series.setNumberOfEpisodes(jsonObject.getString("number_of_episodes"));
            series.setNumberOfSeasons(jsonObject.getString("number_of_seasons"));
            series.setInProduction(jsonObject.getString("in_production"));


            JSONArray seasons=(JSONArray) jsonObject.getJSONArray("seasons");

            ArrayList<SeriesListVO> seriesListArray= new ArrayList<SeriesListVO>();
            for(int i=0;i<seasons.length();i++){


                SeriesListVO seriesListVO= new SeriesListVO();

                seriesListVO.setTitle(seasons.getJSONObject(i).getString("name"));
                seriesListVO.setOverview(seasons.getJSONObject(i).getString("overview"));
                seriesListVO.setEpisodeCount(seasons.getJSONObject(i).getString("episode_count"));
                seriesListVO.setSeasonNumber(seasons.getJSONObject(i).getString("season_number"));
                seriesListVO.setId(seasons.getJSONObject(i).getString("id"));
                seriesListVO.setAirDate(seasons.getJSONObject(i).getString("airDate"));
                seriesListVO.setName(seasons.getJSONObject(i).getString("name"));
                seriesListVO.setPosterPath(seasons.getJSONObject(i).getString("poster_path"));

                seriesListArray.add(seriesListVO);
            }


                series.setSeriesList(seriesListArray);





        } catch (JSONException e) {
            e.printStackTrace();
        }

        return series;

    }
}
