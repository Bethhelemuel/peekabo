package com.peekaboo.spacehead.peekaboo.Utils;


import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.KnownForModel;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 09/02/16.
 */
public abstract class Model {


    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int AUDIO_TYPE=2;

    public int type;
    public int data;
    public String text;

    public void setType(int type) {
        this.type = type;
    }


//
//    public Model(int type, String text, int data)
//    {
//        this.type=type;
//        this.data=data;
//        this.text=text;
//
//    }



    public String title;
    public String poster;
    public String overview;
    public String releaseDate;
    public String vote;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }




            //People


    public String name;
    public String profilePath;

    public ArrayList<KnownForModel> knownForModel;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public ArrayList<KnownForModel> getKnownForModel() {
        return knownForModel;
    }

    public void setKnownForModel(ArrayList<KnownForModel> knownForModel) {

        this.knownForModel=null;
        this.knownForModel = knownForModel;
    }


}
