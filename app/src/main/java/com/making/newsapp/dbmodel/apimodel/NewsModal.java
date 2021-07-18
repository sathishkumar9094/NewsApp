package com.making.newsapp.dbmodel.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsModal {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResult")
    @Expose
    private int totalResults;

    @SerializedName("articles")
    @Expose
    private ArrayList<Artical> articals;

    public NewsModal(String status, int totalResults, ArrayList<Artical> articals) {
        this.status = status;
        this.totalResults = totalResults;
        this.articals = articals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Artical> getArticals() {
        return articals;
    }

    public void setArticals(ArrayList<Artical> articals) {
        this.articals = articals;
    }


}
