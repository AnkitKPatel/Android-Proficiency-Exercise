package com.ankit.testappankit;
import com.ankit.testappankit.adapters.pojo.FeedPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ANKITKP on 2018-03-16.
 */

public interface APIInterface {

// For getting Feed data
    @GET("facts.json")
    Call<FeedPOJO> getFeeds();
}
