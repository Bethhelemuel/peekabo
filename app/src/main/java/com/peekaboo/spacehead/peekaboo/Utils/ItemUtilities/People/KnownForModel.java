package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People;

import android.os.Parcelable;

import java.io.Serializable;

public  class KnownForModel implements Serializable {


    public String title;
    public String posterPath;
    public String overview;
    public String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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
}
