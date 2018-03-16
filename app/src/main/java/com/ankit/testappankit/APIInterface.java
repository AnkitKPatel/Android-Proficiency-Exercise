package com.ankit.testappankit;

import com.ankit.testappankit.adapters.pojo.FeedPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The interface Api interface.
 */
public interface APIInterface {

    /**
     * Gets feeds.
     *
     * @return the feeds
     */
// For getting Feed data
    @GET("facts.json")
    Call<FeedPOJO> getFeeds();
}
