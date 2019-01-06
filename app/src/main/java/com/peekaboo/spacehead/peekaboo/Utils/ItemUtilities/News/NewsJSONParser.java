package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News;

import android.util.Log;

import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Troy on 5/10/2018.
 */

public class NewsJSONParser {


    static ArrayList<NewsVO> news;


    public static ArrayList<NewsVO> parseJSON(String json){

        news= new ArrayList<NewsVO>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray results= (JSONArray) jsonObject.getJSONArray("articles");

            for(int i=0;i<results.length();i++){


                NewsVO newsModel= new NewsVO();

                newsModel.setTitle(results.getJSONObject(i).getString("title"));
                newsModel.setAuthor(results.getJSONObject(i).getString("author"));
                newsModel.setDescription(results.getJSONObject(i).getString("description"));
                newsModel.setUrl(results.getJSONObject(i).getString("url"));
                newsModel.setPoster(results.getJSONObject(i).getString("urlToImage"));
                newsModel.setPublishedDate(results.getJSONObject(i).getString("publishedAt"));

                newsModel.setSource(results.getJSONObject(i).getJSONObject("source").getString("name"));









                news.add(newsModel);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;

    }
}
