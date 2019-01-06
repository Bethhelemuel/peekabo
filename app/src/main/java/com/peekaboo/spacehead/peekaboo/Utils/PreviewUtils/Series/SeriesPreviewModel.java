package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Series;

import java.util.ArrayList;

public class SeriesPreviewModel {

   public String homepage;
   public String numberOfEpisodes;
   public String numberOfSeasons;
   public ArrayList<SeriesListVO> seriesList;
   public String status;
   public String inProduction;
   public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<SeriesListVO> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(ArrayList<SeriesListVO> seriesList) {
        this.seriesList = seriesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInProduction() {
        return inProduction;
    }

    public void setInProduction(String inProduction) {
        this.inProduction = inProduction;
    }
}