package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.People;

import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesListVO;
import com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series.SeriesPreviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Troy on 5/10/2018.
 */

public class PeoplePreviewParser {


    static PeoplePreviewModel person;


    public static PeoplePreviewModel parseJSON(String json){

        person= new PeoplePreviewModel();

        try {
            JSONObject jsonObject = new JSONObject(json);



            person.setBio(jsonObject.getString("biography"));
            person.setPlaceOfBirth(jsonObject.getString("place_of_birth"));
            person.setBirthday(jsonObject.getString("birthday"));






        } catch (JSONException e) {
            e.printStackTrace();
        }

        return person;

    }
}
