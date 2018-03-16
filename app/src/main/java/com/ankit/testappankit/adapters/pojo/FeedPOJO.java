package com.ankit.testappankit.adapters.pojo;

import com.ankit.testappankit.adapters.model.Feed;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Feed pojo.
 */
public class FeedPOJO {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private ArrayList<Feed> feeds = null;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets feeds.
     *
     * @return the feeds
     */
    public ArrayList<Feed> getFeeds() {
        return feeds;
    }

    /**
     * Sets feeds.
     *
     * @param feeds the feeds
     */
    public void setFeeds(ArrayList<Feed> feeds) {
        this.feeds = feeds;
    }

}
