package com.ankit.testappankit.adapters.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ankit.testappankit.adapters.model.Feed;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ANKITKP on 2018-03-16.
 */

public class FeedPOJO {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private ArrayList<Feed> feeds = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(ArrayList<Feed> feeds) {
        this.feeds = feeds;
    }

}
