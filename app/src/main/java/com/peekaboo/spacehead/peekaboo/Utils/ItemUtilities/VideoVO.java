package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities;

import com.peekaboo.spacehead.peekaboo.Utils.Model;

public class VideoVO extends Model {


    public VideoVO(){

      type=1;
    }

    @Override
    public void setVote(String vote) {
        super.setVote(vote);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setOverview(String overview) {
        super.setOverview(overview);
    }

    @Override
    public void setPoster(String poster) {
        super.setPoster(poster);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        super.setReleaseDate(releaseDate);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setType(int type) {
        super.setType(type);
    }

    @Override
    public String getReleaseDate() {
        return super.getReleaseDate();
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getOverview() {
        return super.getOverview();
    }

    @Override
    public String getPoster() {
        return super.getPoster();
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public String getVote() {
        return super.getVote();
    }

}
