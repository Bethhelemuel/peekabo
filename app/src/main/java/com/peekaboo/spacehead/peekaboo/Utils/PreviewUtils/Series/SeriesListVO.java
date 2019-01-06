package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeriesListVO {

    public String episodeCount;
    public String id;
    public String title;
    public String posterPath;
    public String overview;
    public String seasonNumber;
    public String airDate;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd /MMMM/yyyy");
        String dateInString = this.airDate;


        try {

            Date date = formatter.parse(dateInString);

            return date.toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return this.airDate;
        }



    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(String seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(String episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
