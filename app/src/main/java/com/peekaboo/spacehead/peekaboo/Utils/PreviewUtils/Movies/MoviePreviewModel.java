package com.peekaboo.spacehead.peekaboo.Utils.PreviewUtils.Movies;

import com.peekaboo.spacehead.peekaboo.Utils.Utility;

public class MoviePreviewModel {

    public String tagline;
    public String budget;
    public String homepage;
    public String status;
    public String revenue;

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getBudget() {

       if(this.budget == null || this.budget.toString() == "0"){

           return "Don't know ";
       }else{

           double number=Double.parseDouble(this.budget);

           String numberFormat= Utility.format(number);

           return numberFormat;
       }

    }

    public void setBudget(String budget) {



        this.budget = budget;
    }

    public String getHomepage() {

        if (homepage == null) {

            return "null";
        } else {
            return homepage;

        }
    }
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRevenue() {

        if(this.revenue == null || this.revenue.toString() =="0"){

            return "Don't know";

        }else{

            double number=Double.parseDouble(this.revenue);

            String numberFormat= Utility.format(number);

            return numberFormat;
        }

    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
//    public String[] genres;
//    public String[] productionCompanies;
//    public String[] countries;
}