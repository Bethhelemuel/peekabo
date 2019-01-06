package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People;


import com.peekaboo.spacehead.peekaboo.Utils.Model;

import java.util.ArrayList;

public class PeopleVO extends Model {

    public PeopleVO(){

        type=0;
    }

    public String name;
    public String profilePath;
    public String id;
    public ArrayList<KnownForModel> knownForModel;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getProfilePath() {
        return profilePath;
    }

    @Override
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public ArrayList<KnownForModel> getKnownForModel() {
        return knownForModel;
    }

    @Override
    public void setKnownForModel(ArrayList<KnownForModel> knownForModel) {
        this.knownForModel=null;
        this.knownForModel = knownForModel;
    }


}
